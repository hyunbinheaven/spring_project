var mapContainer = document.getElementById('map');
var mapOption = {
	center : new kakao.maps.LatLng(35.970511, 127.620832),
	level : 13
};

var map = new kakao.maps.Map(mapContainer, mapOption);

var likeData = [];
var rstXElements = document.querySelectorAll(".rstx");
var rstYElements = document.querySelectorAll(".rsty");

for (var i = 0; i < rstXElements.length; i++) {
	var lat = parseFloat(rstYElements[i].value);
	var lng = parseFloat(rstXElements[i].value);
	likeData.push({
		latlng : new kakao.maps.LatLng(lat, lng)
	});
}

var imageSrc = "/foodexp/images/foodexplore_map.png";
var imageSize = new kakao.maps.Size(60, 62);
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

var overlays = [];

for (var i = 0; i < likeData.length; i++) {
	var marker = new kakao.maps.Marker({
		map : map,
		position : likeData[i].latlng,
		image : markerImage
	});

	// 각 오버레이에 고유한 클릭 이벤트 핸들러가 있는지 확인하기 위해 클로저를 생성합니다.
	(function(i) {
		var rstId = document.querySelectorAll(".rstid")[i].value;
		var rstName = document.querySelectorAll(".rstname")[i].value;
		var rstAddr = document.querySelectorAll(".rstaddr")[i].value;
		var revStarAvg = document.querySelectorAll(".revstaravg")[i].value;
		var revCount = document.querySelectorAll(".revcount")[i].value;
		var content = '<div class="wrap">' +
        '    <div class="info">' +
        '        <div class="rsthead">' +
        '            <a class="rstname" href="/foodexp/rst/rst_detail?rst_id=' + rstId + '">' + rstName + '</a>' +
        '            <div class="close" onclick="closeOverlay(' + i + ')" title="닫기"></div>' +
        '        </div>' +
        '        <hr>' +
        '        <div class="rstbody">' +
        '            <div class="rstrev">' +
        '                <div class="score">' + revStarAvg + '점</div>' +
        '                <div class="star_box">' +
        '                    <div class="rating">' +
        '                        ★★★★★ <span class="rating_star" style="width: ' + (revStarAvg * 20) + '% !important;">★★★★★</span>' +
        '                        <input type="range" value="1" step="1" min="0" max="10">' +
        '                    </div>' +
        '                </div>' +
        '				 <div class="revcount">('+revCount+'건)</div>' +
        '            </div>' +
        '			 <div class="rstaddr">' +
        '			 <img class="locImg" src="/foodexp/images/location.png">' + rstAddr	
        '            </div>' +
        '        </div>' +
        '    </div>' +
        '</div>';

		var overlay = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			position : marker.getPosition()
		});

		overlays.push(overlay);

		overlay.setMap(null);

		kakao.maps.event.addListener(marker, 'click', function() {
			closeAllOverlays();
			overlay.setMap(map);
		});
	})(i);
}

// 특정 인덱스에 해당하는 오버레이를 닫는 함수
function closeOverlay(index) {
	overlays[index].setMap(null);
}

// 모든 오버레이를 닫는 함수
function closeAllOverlays() {
	for (var i = 0; i < overlays.length; i++) {
		overlays[i].setMap(null);
	}
}