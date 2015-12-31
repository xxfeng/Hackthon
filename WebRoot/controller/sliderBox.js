/*
<div id="demo3" class="slideBox">
  <ul class="items">
    <li><a href="http://www.jq22.com/" title="这里是测试标题一"><img src="img/1.jpg"></a></li>
    <li><a href="http://www.jq22.com/" title="这里是测试标题二"><img src="img/2.jpg"></a></li>
    <li><a href="http://www.jq22.com/" title="这里是测试标题三"><img src="img/3.jpg"></a></li>
    <li><a href="http://www.jq22.com/" title="这里是测试标题四"><img src="img/4.jpg"></a></li>
    <li><a href="http://www.jq22.com/" title="这里是测试标题五"><img src="img/5.jpg"></a></li>
  </ul>
</div>

*/

//load tv lastest images 
function getHomeTvs() {
	$('#SldShwGllry0').slideBox({
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'linear',//swing,linear//滚动特效
		delay : 5,//滚动延迟时间，单位：秒
		hideClickBar : false,//不自动隐藏点选按键
		clickBarRadius : 10
	});

	 $.ajax({
	 	type : "GET",
		url : "text/getHomeTvs.htm",
		async : false, // 同步
		success : function(oResponse) {
			var objs=eval("("+oResponse+")");
			if (objs.returnCode==1) { 
				showHomeImages(objs.data);
			}
			else
				alert("失败，请重试！");
			},
			error : function(msg) {
				alert(JSON.stringify(msg));
			}
		});
}

function showHomeImages(data) {
	//$('#SldShwGllry0').empty();
	var target$ = $('#SldShwGllry0');
	htmlcontent = "<ul class=\"items\">";
	for(i=0;i<data.length;i++) {
		 htmlcontent+="<li><a href=\""+data[i].IMAGE_URL+"\" title=\""+data[i].NAME+"\"> <img src=\""+data[i].IMAGE_URL+"\"></a></li>";
		 //PDATES
	}
	htmlcontent+="</ul>";
	
	target$.innerHTML = htmlcontent;
}


