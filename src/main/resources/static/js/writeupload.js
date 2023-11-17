$(document).ready(function () {

    var maxSize = 5242880;  // 5MB

    function checkExtention (fileName, fileSize) {
        if (fileSize >= maxSize) {
            alert("파일 사이즈 초과");
            $("#files").val("");
            return false;
        }

        return true;
    }

    $('form').submit(function (event) {
        event.preventDefault(); // 폼 제출을 방지하여 페이지 리로딩 방지

        // FormData 객체를 생성하여 파일 업로드 데이터를 포함시킵니다.
        // var formData = new FormData($(this)[0]);
        
        var form = $(this); // 폼 요소 저장

        var formData = new FormData();
		var files = $("#files").get(0).files;

        //파일이 있으면 ajax 후 업로드
        if (files.length > 0) {

            for (var i = 0; i < files.length; i++) {
                if (!checkExtention(files[i].name, files[i].size)) {
                    $("#files").val("");
                    return false;
                }
                formData.append("files", files[i]);
            }



            $.ajax({
                url: "/foodexp/upload", // 요청을 보낼 URL을 지정하세요.
                type: "POST",
                data: formData,
                processData: false, // 데이터 처리를 jQuery에 맡깁니다.
                contentType: false, // 데이터 유형을 jQuery에 맡깁니다.
                success: function (data) {
                    var uploadUL = $(".uploadResult ul");

                    data.forEach(function (fileInfo, i) {
                        var liElement = $("<li>");

                        var hiddenInputFileName = $("<input>");
                        hiddenInputFileName.attr("type", "hidden");
                        hiddenInputFileName.attr("name", "attachList[" + i + "].uploadFileName");
                        hiddenInputFileName.attr("value", fileInfo.uploadFileName);

                        var hiddenInputUploadPath = $("<input>");
                        hiddenInputUploadPath.attr("type", "hidden");
                        hiddenInputUploadPath.attr("name", "attachList[" + i + "].uploadFilePath");
                        hiddenInputUploadPath.attr("value", fileInfo.uploadFilePath);

                        liElement.append(hiddenInputFileName);
                        liElement.append(hiddenInputUploadPath);
                        uploadUL.append(liElement);

                    });

                    // Ajax 요청이 완료된 후 폼 제출을 수동으로 실행
                    form[0].submit();
                }
            });//end ajax

        }else{
            form[0].submit();
        }

    });
});
