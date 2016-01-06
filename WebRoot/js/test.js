var user = new Object();
 user.username = "test";
 user.password = "123456";
 user.firstname = "test";
 user.lastname ="account";
 function newUser(user)
 {
   $.postJSON("user/register", user, function(
       username)
   {
     console.debug("Inserted: " + username);
   });
 }
 $.postJSON = function(url, data, callback) {
	    return jQuery.ajax({
	    headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
	    'type': 'POST',
	    'url': url,
	    'data': JSON.stringify(data),
	    'dataType': 'json',
	    'success': callback
	    });
	};