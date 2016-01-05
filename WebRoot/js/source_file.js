$(document).ready(function(){
	$("#sumitUploadFile").bind("click", uploadFile);
});

function uploadFile() {
    var fileObj = $("#selectedFile")[0].files[0];

    var requestURL =  "REST/source/upload";
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
