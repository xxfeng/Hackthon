$(document).ready(function(){
	$("#addOrder").bind("click", addOrder);
	$("#searchOrderByOrderId").bind("click", searchOrderByOrderId);
	$("#searchOrderByUser").bind("click", searchOrderByUser);
	$("#searchOrderByStatus").bind("click", searchOrderByStatus);
});


var gData = {};


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
	        data:JSON.stringify({ "user":{"userid":2}}), //admin -1; other people:userid
			success: getOrderSuccess,
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


function showDishs(index) {
	order = strToJson(gData)[index];
	
	dishs = order.dishNameList;
	dishNums = order.dishNumList;
	html="";
	
	html += '<p>menu</p>';
	
	html +=  '<table class="table">' +
	'<thead>   <tr>      <th>#</th>      <th>Dish</th>      <th>Qty</th>      <th>Price</th>    </tr><thead>';
	  
	html += '<tbody>'
    	
	for(i=0;i<dishs.length;i++) {
		html += '<tr class="active"> <td scope="row">'+(i+1)+'</td><td>'+dishs[i].name+'</td><td>X'+dishNums[i]+'</td><td>'+dishs[i].price+'</td></tr>';
	}
	html += '</tbody></table>'
	
	$("#dishs")[0].innerHTML=html;
}

//------------------------------------------------------------
function getOrderSuccess(data){
	orders = strToJson(data);
	gData=data;
	
	html ='<table class="table table-striped table-hover" >'+
	'<thead> <tr>'+
			'<th>#No</th><th>Order No</th><th>Booktime</th><th>Table</th><th>People</th><th>Dishs</th><th>Total</th><th>Status</th></tr></thead>';
	
	html+='<tbody>';
	for(i=0;i<orders.length;i++) {
		
		html+='<tr>';
		html+='<td>'+(i+1)+'</td>';
		html+='<td>'+orders[i].orderNo+'</td>';
		html+='<td>'+orders[i].bookTime+'</td>';
		html+='<td>wait to add</td>';
		html+='<td>'+orders[i].numPeople+'</td>';
		html+='<td>'+orders[i].dishNameList.length+'</td>';
		html+='<td>'+orders[i].totalValue+'</td>';
		html+='<td>'+orders[i].status+'</td>';
		html+='<td><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick=showDishs("'+i+'")>Dismiss</button></td>';
		html+='</tr>';
	}
	html += '</tbody></table>';

	$("#orderlist")[0].innerHTML=html;
//	alert(strToJson(data)[0].order_id);
}

function addOrderFail(data){
//	alert(data);
}






