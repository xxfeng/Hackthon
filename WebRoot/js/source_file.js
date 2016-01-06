$(document).ready(function(){
	$("#sumitUploadFile").bind("click", uploadFile);
	$("#triggerTestPostJSONAJAX").bind("click", testPostJSON);
});

function uploadFile() {
    var fileObj = $("#selectedFile")[0].files[0];

    var requestURL =  "resource/upload";
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

function testPostJSON(){
	var queryStr = {params:"hello"};
	var requestURI = "resource/testPost";
	var opts = {
	        url: requestURI,
	        data:queryStr,
	        type: 'POST',
//	        headers: reqHeaders,
	        success: self.handleQueryCommand,
	        error: self.handleQueryCommandError
	    };
	$.ajax(opts);
}
self.handleQueryCommand = function(data){
	alert(data);
}

self.handleQueryCommandError = function(){
	alert("post fail");
}
self.handleUploadFileSuccess = function(data){
	alert(data);
}

self.handleUploadFileError = function() {
    alert("file upload failure");
}
