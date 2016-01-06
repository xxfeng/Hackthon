$(document).ready(function(){
	$("#addOrder").bind("click", addOrder);
});

function addOrder(){
	var requestURI = "resource/order/add";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
	        data:JSON.stringify({ "order_id": "1","user":{"userid":"2"},"dishNameList":[{"dish_id":"1"},{"dish_id":"2"}],"dishNumList":["1","2"]}),
			success: self.addOrderSuccess,
		    error: self.addOrderFail
	};
	$.ajax(opts);
}

function deleteOrder(){
	
}

function modifyOrder(){
	
}

function searchOrder(){
	
}
//------------------------------------------------------------
function addOrderSuccess(){
	
}

function addOrderFail(){
	
}