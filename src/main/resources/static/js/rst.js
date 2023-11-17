// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		level: 6
		// 지도의 확대 레벨
	};

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(map);

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1, disableAutoPan: true });

// 내위치
var myLocationMarker;



// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);




// 검색 form의 리턴값을 없애기 위한 javascript 동작

document.addEventListener("DOMContentLoaded", function () {
	var myForm = document.getElementById("searchForm");

	myForm.onsubmit = function (event) {
		event.preventDefault(); // 폼 제출 기본 동작 방지
		searchPlaces();
		console.log("Hello, world!"); // 콘솔에 메시지 출력
	};
});




// ******여기 위치에 post get 등등으로 내위치로검색, 전국검색을 나눠야할 기능이 실행되어야함
var inputLoadSelector = document.getElementById("inputLoadSelector").value;
var selectRadio
if (inputLoadSelector == "myLocation") {
	selectRadio = document.querySelector('input[name="searchType"][value="myLocation"]');
} else if (inputLoadSelector == "nationwide") {
	selectRadio = document.querySelector('input[name="searchType"][value="nationwide"]');
} else {
	selectRadio = document.querySelector('input[name="searchType"][value="myMap"]');
}
selectRadio.checked = true;


//처음으로 페이지를 로드할떄 검색을 시작한다.
searchPlaces()

//내위치에 마커를 생성합니다
function myLocationMapView() {
	var onSuccess = function (position) {
		var my_lat = position.coords.latitude;
		var my_lng = position.coords.longitude;
		var myLocation = new kakao.maps.LatLng(my_lat, my_lng);

		// 이전 마커가 존재하는지 확인
		if (myLocationMarker) {
			// 이전 마커가 존재한다면 제거
			myLocationMarker.setMap(null);
		}

		// 새로운 현재 위치 마커 생성
		myLocationMarker = new kakao.maps.Marker({
			map: map,
			position: myLocation,
			title: '현재 위치'
		});

		// 지도 중심 및 확대 레벨 설정
		map.setCenter(myLocation);
		map.setLevel(6);


		//다시 자기위치로 지정
		// 첫 번째 searchFunction 실행 후 이어지는 동작

		searchFunction();

		setTimeout(function () {
			map.setCenter(myLocation);
			map.setLevel(6);
		}, 100);


	};

	var onError = function (error) {
		console.error('내 위치 정보를 가져오는 데 실패했습니다.', error);

		// 위치 정보를 가져오지 못했을 때도 검색을 시작
		searchFunction();
	};

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(onSuccess, onError);
	} else {
		console.error('브라우저가 Geolocation을 지원하지 않습니다.');

		// 위치 정보를 가져오지 못했을 때도 검색을 시작
		searchFunction();
	}
}




// 확대,축소,이동시 재검색할지 설정
var isWheel = false;

// 지도 선택시 실시간으로 새로고침할지 설정
kakao.maps.event.addListener(map, 'idle', searchPlacesWheel);

// 키워드 검색을 요청하는 함수입니다
function searchFunction() {

	var keyword = document.getElementById('searchinput').value;

	var selectCate = $("input[type='radio'][name='shop']:checked").next("label").text();

	var h_area1 = "", h_area2 = "", h_area3 = "";

	var searchType = document.querySelector('input[name="searchType"]:checked').value;
	if (searchType === "nationwide") {
		//여기일 경우 h_area1, h_area2의 보이지 않는 옵션을 풀고, 텍스트값을 h_area1, h_area2에 넣음
		h_area1 = document.getElementById('h_area1');
		h_area2 = document.getElementById('h_area2');
		h_area3 = document.getElementById('h_area3');

		h_area1.hidden = false;
		h_area2.hidden = false;
		h_area3.hidden = false;

		h_area1 = document.getElementById('h_area1').selectedIndex;
		h_area2 = document.getElementById('h_area2').selectedIndex;
		h_area3 = document.getElementById('h_area3').selectedIndex;

		console.log(`h_area1: ${h_area1}`);
		console.log(`h_area2: ${h_area2}`);
		console.log(`h_area3: ${h_area3}`);
		if (h_area1 == 0) {
			h_area1 = "";
		}
		else {
			h_area1 = document.getElementById('h_area1').options[document.getElementById('h_area1').selectedIndex].text;
		}
		if (h_area2 == 0) {
			h_area2 = "";
		}
		else {
			h_area2 = document.getElementById('h_area2').options[document.getElementById('h_area2').selectedIndex].text;
		}
		if (h_area3 == 0) {
			h_area3 = "";
		}
		else {
			h_area3 = document.getElementById('h_area3').options[document.getElementById('h_area3').selectedIndex].text;
		}

	}
	else if (searchType === "myLocation" || searchType === "myMap") {
		//여기일 경우 h_area1, h_area2를 보이지 않게 옵션을 정하고, 그대로 둠
		h_area1 = document.getElementById('h_area1');
		h_area2 = document.getElementById('h_area2');
		h_area3 = document.getElementById('h_area3');

		h_area1.hidden = true;
		h_area2.hidden = true;
		h_area3.hidden = true;

		h_area1 = "";
		h_area2 = "";
		h_area3 = "";
	}

	//nationwide
	//myLocation
	//myMap


	if (selectCate === "전체") {
		selectCate = "";
	}

	keyword = keyword + " " + h_area1 + " " + h_area2 + " " + h_area3 + " " + selectCate;
	console.log(`1번: ${keyword}`);

	// 공백을 압축
	keyword = keyword.replace(/\s+/g, ' ');
	console.log(`2번: ${keyword}`);

	if (!keyword.replace(/^\s+|\s+$/g, '')) {
		ps.categorySearch('FD6', placesSearchCB, { useMapBounds: true });
		console.log(`3-1번: ${keyword}`);
	} else {
		keyword = keyword + " 음식점";
		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
		ps.keywordSearch(keyword, placesSearchCB, { useMapBounds: true });
		console.log(`3-2번: ${keyword}`);
	}
}



// 라디오 버튼으로 검색 유형 선택
function searchPlaces() {
	var searchType = document.querySelector('input[name="searchType"]:checked').value;

	isWheel = false;
	ps = new kakao.maps.services.Places();

	if (searchType === "nationwide") {
		searchFunction();
	} else if (searchType === "myLocation") {
		searchPlaceMine();
	} else if (searchType === "myMap") {
		searchPlacesWheel();
	}
}

function searchPlacesWheel() {
	var searchType = document.querySelector('input[name="searchType"]:checked').value;
	if (searchType === "myMap") {
		isWheel = true;
		ps = new kakao.maps.services.Places(map);
		searchFunction();
	}
}

function searchPlaceMine() {
	isWheel = true;
	ps = new kakao.maps.services.Places(map);
	myLocationMapView();
}



// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
	if (status === kakao.maps.services.Status.OK) {

		// 정상적으로 검색이 완료됐으면
		// 검색 목록과 마커를 표출합니다
		displayPlaces(data);

		// 페이지 번호를 표출합니다
		displayPagination(pagination);

	} else if (status === kakao.maps.services.Status.ZERO_RESULT) {
		return;

	} else if (status === kakao.maps.services.Status.ERROR) {
		return;
	}
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
	var listEl = document.getElementById('placesList'),
		menuEl = document.getElementById('menu_wrap'),
		fragment = document.createDocumentFragment(),
		bounds = new kakao.maps.LatLngBounds(), listStr = '';

	// 검색 결과 목록에 추가된 항목들을 제거합니다
	removeAllChildNods(listEl);

	// 지도에 표시되고 있는 마커를 제거합니다
	removeMarker();
	for (var i = 0; i < places.length; i++) {

		// 마커를 생성하고 지도에 표시합니다
		var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
			marker = addMarker(placePosition, i),
			itemEl = getListItem(i, places[i]);

		// 검색 결과 항목 Element를 생성합니다


		// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		// LatLngBounds 객체에 좌표를 추가합니다

		bounds.extend(placePosition);

		// 마커와 검색결과 항목에 mouseover 했을때
		// 해당 장소에 인포윈도우에 장소명을 표시합니다
		// mouseout 했을 때는 인포윈도우를 닫습니다
		(function (marker, title, i, placePosition) {
			kakao.maps.event.addListener(marker, 'mouseover',
				function () {
					displayInfowindow(marker, title);
				});

			kakao.maps.event.addListener(marker, 'mouseout',
				function () {
					infowindow.close();
				});

			itemEl.onmouseover = function () {
				displayInfowindow(marker, title);

			};

			itemEl.onmouseout = function () {
				infowindow.close();
			};
			itemEl.onclick = function () {
				map.setCenter(placePosition);
				map.setLevel(6);
			};

		})(marker, places[i].place_name, i, placePosition);



		//여기서 javascript - OracleDB로 전송을 위한 함수를 구현해야한다.


		(function () {
			if (i == 0) {

				//00. DEBUG // places to JSON 출력하기
				//console.log(`places:${places[i]}`);
				//console.log(`places: ${JSON.stringify(places[i])}`);


				//01. fetch함수 사용하여 전송하기
				fetch('/foodexp/htmltodb', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json',
						'charset': 'UTF-8'
					},
					body: JSON.stringify(places)
				})
					.then(response => { })
					.catch(error => {
						console.error("Error:", error);
					});

			}
		})();







		fragment.appendChild(itemEl);
	}

	// 검색결과 항목들을 검색결과 목록 Element에 추가합니다
	listEl.appendChild(fragment);
	menuEl.scrollTop = 0;

	// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	if (!isWheel) {
		map.setBounds(bounds);
	}
}
/*

id					String		장소 ID		-> kakao   유니크 
place_name			String		장소명, 업체명
category_name		String		카테고리 이름
category_group_code	String		중요 카테고리만 그룹핑한 카테고리 그룹 코드
category_group_name	String		중요 카테고리만 그룹핑한 카테고리 그룹명
phone				String		전화번호
address_name		String		전체 지번 주소
road_address_name	String		전체 도로명 주소
x					String		X 좌표값, 경위도인 경우 longitude (경도)
y					String		Y 좌표값, 경위도인 경우 latitude(위도)
place_url			String		장소 상세페이지 URL
distance			String		중심좌표까지의 거리 (단, x,y 파라미터를 준 경우에만 존재)
								단위 meter
*/
// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
	var el = document.createElement('li'), itemStr = '<span class="markerbg marker_'
		+ (index + 1)
		+ '"></span>'
		+ '<div class="info">'
		+ '   <a href="/foodexp/rst/rst_detail?rst_id=' + places.id + '"><h5>' + places.place_name + '</h5></a>'
		;

	if (places.road_address_name) {
		itemStr += '    <span>' + places.road_address_name + '</span>'
			+ '   <span class="jibun gray">' + places.address_name
			+ '</span>';
	} else {
		itemStr += '    <span>' + places.address_name + '</span>';
	}

	itemStr += '  <span class="tel">' + places.phone + '</span>'
		+ '</div>';

	el.innerHTML = itemStr;
	el.className = 'item';

	return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
	var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
		imgOptions = {
			spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
			spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			offset: new kakao.maps.Point(13, 37)
			// 마커 좌표에 일치시킬 이미지 내에서의 좌표
		}, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
			imgOptions), marker = new kakao.maps.Marker({
				position: position, // 마커의 위치
				image: markerImage
			});

	marker.setMap(map); // 지도 위에 마커를 표출합니다
	markers.push(marker); // 배열에 생성된 마커를 추가합니다

	return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
	var paginationEl = document.getElementById('pagination'), fragment = document
		.createDocumentFragment(), i;

	// 기존에 추가된 페이지번호를 삭제합니다
	while (paginationEl.hasChildNodes()) {
		paginationEl.removeChild(paginationEl.lastChild);
	}

	for (i = 1; i <= pagination.last; i++) {
		var el = document.createElement('a');
		el.href = "#";
		el.innerHTML = i;

		if (i === pagination.current) {
			el.className = 'on';
		} else {
			el.onclick = (function (i) {
				return function () {
					pagination.gotoPage(i);
				}
			})(i);
		}

		fragment.appendChild(el);
	}
	paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
	var content = '<div style="width:auto;padding:5px;z-index:1;">' + title
		+ '</div>';

	infowindow.setContent(content);
	infowindow.open(map, marker);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
	while (el.hasChildNodes()) {
		el.removeChild(el.lastChild);
	}
}