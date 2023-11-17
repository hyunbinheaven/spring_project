// const rating_input = document.querySelector('.rating input');
// const rating_star = document.querySelector('.rating_star');

// // 별점 드래그 할 때
// rating_input.addEventListener('input', () => {
// rating_star.style.width = `${rating_input.value * 10}%`;
// });
// $('.star_rating > .star').click(function() {
// 	$(this).parent().children('span').removeClass('on');
// 	$(this).addClass('on').prevAll('span').addClass('on');
// })

/*
 $(document).ready(function() {
 let star = $(".rating_star").attr("data-star");
 console.log(star);
 $(".rating_star").css('width', star * 20 + '%');
 });
 */

$(document).ready(function() {
	// 즐겨찾기가 되어있는지
	var isLiked = $('#isLiked').val();
	var $btnlikes = $('.btnlikes');
	if (isLiked == 1) {
		// 이미 즐겨찾기한 경우
		$btnlikes.find('img').attr('src', '/foodexp/images/star_full.png');
		$btnlikes.addClass('on');
		$btnlikes.css('border', '2px solid #ff8100');
	} else {
		// 아직 즐겨찾기하지 않은 경우
		$btnlikes.find('img').attr('src', '/foodexp/images/star_empty.png');
		$btnlikes.removeClass('on');
		$btnlikes.css('border', '2px solid black');
	}

	// 즐겨찾기 버튼을 눌렀을 때
	$('.btnlikes').click(function() {
	    if ($(this).hasClass('on')) {
	        // 즐겨찾기 해제
	        console.log("해제");
	        $(this).find('img').attr('src', '/foodexp/images/star_empty.png');
	        $(this).removeClass('on');
	        $(this).css('border', '2px solid black');
	        delLikes();
	    } else {
	        // 즐겨찾기 설정
	        if (loginCheck()) {
	            $(this).find('img').attr('src', '/foodexp/images/star_full.png');
	            $(this).addClass('on');
	            $(this).css('border', '2px solid #ff8100');
	            addLikes();
	        } else {
	            alert("로그인이 필요합니다.");
	            window.location.href = '/foodexp/member/loginform';
	        }
	    }
	});
	
	$('.write_rev').click(function() {
		
		var rst_id = $('#rst_id').val();
		if(loginCheck()){
			window.location.href = "/foodexp/rst/revwrite?rst_id=" + rst_id;
		}else{
			alert("로그인이 필요합니다.");
	        window.location.href = '/foodexp/member/loginform';
		}
		
	});

	// 초기 width 합 계산
	var totalWidth = 0;

	// 각 이미지의 width 합 계산
	$('.photos ul li').each(function() {
		totalWidth += $(this).width();
	});

	// width 합이 1130px을 넘으면 가장 마지막 li 삭제
	while (totalWidth > 1130) {
		var lastLi = $('ul li:last-child');
		totalWidth -= lastLi.width();
		lastLi.remove();
	}

	 // 이미지 클릭 시 원본 크기로 보여주기
	 $('.rev_photo ul li img').on('click', function() {
		console.log("click");
		var originalSrc = $(this).attr('src');

		$.fancybox.open({
			src: originalSrc,
			type: 'image'
		});
	});

	$('.photos ul li img').on('click', function() {
		console.log("click");
		var originalSrc = $(this).attr('src');

		$.fancybox.open({
			src: originalSrc,
			type: 'image'
		});
	});

});

function loginCheck() {
    var user_email = document.getElementById('user_email').value;
    if (user_email !== null && user_email !== "") {
        return true; // 로그인 상태
    } else {
        return false; // 비로그인 상태
    }
}

function delLikes() {
	var rst_id = $('#rst_id').val();
	$.ajax({
		type : 'POST',
		url : '/foodexp/rst/delLikes?rst_id=' + rst_id,
		success : function(data) {
			window.location.reload();
		}
	});
}

function addLikes() {
	var rst_id = $('#rst_id').val();
	$.ajax({
		type : 'POST',
		url : '/foodexp/rst/addLikes?rst_id=' + rst_id,
		success : function(data) {
			window.location.reload();
		}
	});
}

