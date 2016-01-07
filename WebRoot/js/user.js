function strToJson(str){ 
	var json = eval('(' + str + ')'); 
	return json; 
}

function login() {
	username = $("#username").val();
	password = $("#password").val();
	
	var queryStr = {"username":username,"password":password};
	 $.ajax({
			url : "user/login",
			async : false, // 同步
	        data:queryStr,
	        type: 'POST',
			
			success : function(oResponse) {
				if (oResponse.returnCode === 0 && oResponse.user.admin===true) { 
//					alert(objs.returnCode+" i got "+objs.user.+" from you.");
					window.location.href='./backend.html';
				}
				else if(oResponse.returnCode === 0 && oResponse.user.admin===false){
					window.location.href='./home.html';
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

function register() {
	firstname = $("#firstname").val();
	lastname = $("#lastname").val();
	organizationname = $("#organizationname").val();
	email = $("#email").val();
	password = $("#password").val();
	
	
	var queryStr = {"firstname":firstname,"lastname":lastname,
			"username":email,"password":password};
	$.ajax({
		url : "user/register",
		async : false, // 同步
        data: JSON.stringify(queryStr),
        type: 'POST',
        contentType:"application/json",
		success : function(oResponse) {
		    var objs=eval("("+oResponse+")");
			if (objs.returnCode==0) { 
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