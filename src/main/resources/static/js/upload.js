$(document).ready(function (e) {
    var maxSize = 5242880;  // 5MB

    function checkExtention (fileName, fileSize) {
        if (fileSize >= maxSize) {
            alert("파일 사이즈 초과");
            $("#files").val("");
            return false;
        }

        return true;
    }

    $(".uploadResult").on("click","li",function(e){
        console.log("view img");
    }) ;

    // 파일 선택이 변경되었을 때 AJAX 호출
	// $("#files").on("change", function () {
	// 	console.log("file changed");

	// 	var formData = new FormData();
	// 	var files = $(this).get(0).files;

	// 	for (var i = 0; i < files.length; i++) {
    //         if (!checkExtention(files[i].name, files[i].size)) {
    //             $("#files").val("");
    //             return false;
    //         }
	// 		formData.append("files", files[i]);
	// 	}
		
	// 	$.ajax({
	// 		url: "/foodexp/upload",
	// 		type: "POST",
	// 		data: formData,
	// 		processData: false,
	// 		contentType: false,
	// 		success: function (data) {

    //             var uploadUL = $(".uploadResult ul");
    //             uploadUL.empty();

    //             var str = "";

    //             data.forEach(function (fileInfo, i) {
    //                 console.log("----------");
    //                 var liElement = $("<li>");
    //                 // liElement.attr("data-path", fileInfo.uploadFilePath);
    //                 // liElement.attr("data-uuid", fileInfo.uuid);

    //                 var divElement = $("<div>");

    //                 var buttonElement = $("<button>");
    //                 buttonElement.attr("type", "button");
    //                 buttonElement.addClass("delbtn");
    //                 buttonElement.data("file", fileInfo.uploadFileName);
    //                 buttonElement.data("path",fileInfo.uploadFilePath);

    //                 var delbtn = $("<img>");
    //                 delbtn.addClass("delimg");
    //                 delbtn.attr("src", "/foodexp/images/xbtn.png");
    //                 buttonElement.append(delbtn); 

    //                 var imgElement = $("<img>");
    //                 imgElement.attr("src", fileInfo.uploadFilePath);
    //                 imgElement.css("height", "150px");
    //                 imgElement.addClass("uploadFilePath");

    //                 var hiddenInputFileName = $("<input>");
    //                 hiddenInputFileName.attr("type", "hidden");
    //                 hiddenInputFileName.attr("name", "attachList[" + i + "].uploadFileName");
    //                 hiddenInputFileName.attr("value", fileInfo.uploadFileName);

    //                 var hiddenInputUploadPath = $("<input>");
    //                 hiddenInputUploadPath.attr("type", "hidden");
    //                 hiddenInputUploadPath.attr("name", "attachList[" + i + "].uploadFilePath");
    //                 hiddenInputUploadPath.attr("value", fileInfo.uploadFilePath);

    //                 divElement.append(buttonElement).append("<br>").append(imgElement);
    //                 liElement.append(divElement);
    //                 liElement.append(hiddenInputFileName);
    //                 liElement.append(hiddenInputUploadPath);
    //                 uploadUL.append(liElement);

    //             });

	// 		}
	// 	});
	// });//end filechang

});