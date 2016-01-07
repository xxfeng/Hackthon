$(document).ready(function(){
	$("#addOrder").bind("click", addOrder);
	$("#searchOrderByOrderId").bind("click", searchOrderByOrderId);
	$("#searchOrderByUser").bind("click", searchOrderByUser);
	$("#searchOrderByStatus").bind("click", searchOrderByStatus);
});

function strToJson(str){ 
	var json = eval('(' + str + ')'); 
	return json; 
}

function addOrder(){
	var requestURI = "resource/order/add";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
	        data:JSON.stringify({ "order_id": "1","user":{"userid":"2"},"dishNameList":[{"dish_id":"1"},{"dish_id":"2"}],"dishNumList":["1","2"]}),
			success: addOrderSuccess,
		    error: addOrderFail
	};
	$.ajax(opts);
}


function deleteOrder(){
	
}

function modifyOrder(){
	
}

function searchOrderById(){
	
}

function searchOrderByOrderId(){
	var requestURI = "resource/order/search";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
	        data:JSON.stringify({ "order_id": "1"}),
			success: addOrderSuccess,
		    error: addOrderFail
	};
	$.ajax(opts);
}

function searchOrderByUser(){
	var requestURI = "resource/order/search";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
	        data:JSON.stringify({ "user":{"userid":-1}}), //admin -1; other people:userid
			success: addOrderSuccess,
		    error: addOrderFail
	};
	$.ajax(opts);
}

function searchOrderByStatus(){
	var requestURI = "resource/order/search";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
	        data:JSON.stringify({ "user":{"userid":-1}, "status":0}), //admin -1; other people:userid
			success: addOrderSuccess,
		    error: addOrderFail
	};
	$.ajax(opts);
}
//------------------------------------------------------------
function addOrderSuccess(data){
//	alert(strToJson(data)[0].order_id);
}

function addOrderFail(data){
//	alert(data);
}