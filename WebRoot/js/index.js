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