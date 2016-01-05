$(document).ready(function(){
	$("#sumitUploadFile").bind("click", uploadFile);
});

function uploadFile() {
    var fileObj = $("#selectedFile")[0].files[0];

    var requestURL =  "REST/upload";
    // FormData
    var form = new FormData();
    form.append("file", fileObj);
    form.append("name", "hello");
    var opts = {
        url: requestURL,
        data: form,
        cache: false,
        contentType: false,
        processData: false,
        type: 'POST',
//        headers: reqHeaders,
        success: self.handleUploadFileSuccess,
        error: self.handleUploadFileError
    };

    if(form.fake) {
        // Make sure no text encoding stuff is done by xhr
        opts.xhr = function() { var xhr = $.ajaxSettings.xhr(); xhr.send = xhr.sendAsBinary; return xhr; }
        opts.contentType = "multipart/form-data; boundary="+form.boundary;
        opts.data = form.toString();
    }
    $.ajax(opts);
}

self.handleUploadFileSuccess = function(data){
	alert(data);
}

self.handleUploadFileError = function() {
    alert("file upload failure");
}

	function recognize()
	{
		//var param=$("#text_area")[0].value;
		var param='Test1';
		 $.ajax({
				type : "GET",
				url : "text/reg.htm?param="+param,
				async : false, // 同步
				success : function(oResponse) {
				    var objs=eval("("+oResponse+")");
					if (objs.returnCode==1) { 
						alert(objs.result+" i got "+objs.got+" from you.");
					}
					// else if(oResponse.iErrCode!=0)
					// alert(oResponse.sDescript);
					else
						alert("失败，请重试！");
				},
				error : function(msg) {
					alert(JSON.stringify(msg));
				}
			});
		
		
		
		
	}



function showWords(data)
{
	$("#result_word")[0].innerHTML=data.result;

}