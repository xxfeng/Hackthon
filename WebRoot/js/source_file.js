 function uploadFile() {
         var fileObj = $("#selectedFile")[0].files[0];

         var requestURL =  "./REST/source/upload";
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
//             headers: reqHeaders,
             success: self.handleQueryRecommendParserListSuccess,
             error: self.handleQueryRecommendParserListError
         };

         if(form.fake) {
             // Make sure no text encoding stuff is done by xhr
             opts.xhr = function() { var xhr = $.ajaxSettings.xhr(); xhr.send = xhr.sendAsBinary; return xhr; }
             opts.contentType = "multipart/form-data; boundary="+form.boundary;
             opts.data = form.toString();
         }
         $.ajax(opts);
     }
     
     self.handleQueryRecommendParserListSuccess = function(data){
//          alert(data.msg)
		alert(data);
		alert("upload success");
     }
     
     self.handleQueryRecommendParserListError = function() { 
         alert("file upload failure");
     }