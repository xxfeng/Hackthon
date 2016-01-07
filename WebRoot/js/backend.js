$(document).ready(function(){
	  $('[data-toggle="popover"]').popover();
});


$(document).ready(function(){
	$("#addOrder").bind("click", addOrder);
	$("#searchOrderByOrderId").bind("click", searchOrderByOrderId);
	$("#searchOrderByUser").bind("click", searchOrderByUser);
	$("#searchOrderByStatus").bind("click", searchOrderByStatus);
});




var gData = null;
var gorderid;
var gtables;
var ggord;


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

function updateSubmitStatus() {
	$('#myOrder').modal('hide');
	//$('.modal-content').hide()
	searchOrderByUser();
	bootbox.alert("orders updated successful, we will notice the User!");
}

function updateAssignSubmitStatus() {
	$('#myOrder1').modal('hide');
	alert("orders updated successful, we will notice the User!");
	//$('.modal-content').hide()
	searchOrderByUser();

}


function searchOrderByUser(){
	var requestURI = "resource/order/search";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
	        data:JSON.stringify({ "user":{"userid":-1}}), //admin -1; other people:userid
			success: searchOrderByStatus,
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
			success: showBookedOrders,
		    error: addOrderFail
	};
	$.ajax(opts);
}

function setOrderStatus() {
	status = $("#selectstatus").val();
	var requestURI = "resource/order/modify";
	if ( status == "change to accepted" ) {
		var opts = {
		        type: "POST",
		        url:requestURI,
		        contentType: "application/json",
		        data:JSON.stringify({ "order_id": gorderid,"user":{"userid":-1},"status":1}), //admin -1; other people:userid
				success: updateSubmitStatus,
			    error: addOrderFail
		};
	}
	else if (status == "change to rejected") {
		var opts = {
		        type: "POST",
		        url:requestURI,
		        contentType: "application/json",
		        data:JSON.stringify({ "order_id": gorderid,"user":{"userid":-1},"status":3}), //admin -1; other people:userid
				success: updateSubmitStatus,
			    error: addOrderFail
		};
	}
	
	$.ajax(opts);
}



function showBookedOrderDetails(id) {
	orders = strToJson(gData);
	index=0;
	for(i=0; i<orders.length;i++){
		if(orders[i].order_id==id) {
			index = i;
			break;
		}
	}
	
	order = strToJson(gData)[index];
	
	dishs = order.dishNameList;
	dishNums = order.dishNumList;
	html="";
	
	html += '<p>menu</p>';
	
	html +=  '<table class="table">' +
	'<thead>   <tr>      <th>#</th>      <th>Dish</th>      <th>Qty</th>      <th>Price</th>    <th>User</th> </tr><thead>';
	  
	html += '<tbody>'
    	
	for(i=0;i<dishs.length;i++) {
		
		html += '<tr class="active"> <td scope="row">'+(i+1)+'</td><td>'+dishs[i].name+'</td><td>X'+dishNums[i]+'</td><td>'+dishs[i].price+'</td>';
		html += '<td> <img src="./lib/image/el_psy_congroo.jpg" alt="el_psy_congroo" class="meal_pic"/> </td></tr>';
	}
	html += '</tbody></table>'
	
	$("#showorderdetails")[0].innerHTML=html;
}

function setOrderID( id ) {
	gorderid = id;
	showBookedOrderDetails(id);
}


//------------------------------------------------------------
function showBookedOrders(data){
	orders = strToJson(data);
	var changedOrders = new Array();
	if(gData!=null) {
		neworders = orders;
		oldorders = strToJson(gData);

		for(i=0;i<neworders.length;i++) {
			flag=0;
			for(j=0;j<oldorders.length;j++) {
				if( neworders[i].order_id == oldorders[j].order_id ) {
					continue;
				}
			}
			if( j == oldorders.length )
				changedOrders.push(i);
		}
		
		if( changedOrders.length>0) {
			alert("Exists New Orders, please check!");
		}
		else
			return;
		
	}
	
	gData=data;
	
	html ='<table class="table" >'+
	'<thead> <tr>'+
			'<th>#No</th><th>Order No</th><th>Booktime</th><th>Table</th><th>People</th><th>Dishs</th><th>Total</th></tr></thead>';
	
	html+='<tbody>';
	for(i=0;i<orders.length;i++) {
		cstr='<tr>';
		for(j=0;j<changedOrders.length;j++) {
			if(i==changedOrders[j])
				cstr = '<tr style="background-color:bisque">';
		}
		
		html+= cstr;
		
		html+='<td>'+(i+1)+'</td>';
		html+='<td>'+orders[i].orderNo+'</td>';
		html+='<td>'+orders[i].bookTime+'</td>';
		
		
		orders[i].numPeople = 4;
		if(orders[i].tableName && orders[i].tableName.length>0)
			html+='<td>'+orders[i].tableName+'</td>';
		else
			html+='<td><button type="button" class="btn" data-toggle="modal" data-target="#myOrder1" onclick=getAssignTable('+orders[i].numPeople+','+orders[i].order_id+')>Asign Table</button></td>';
		
		html+='<td>'+orders[i].numPeople+'</td>';
		html+='<td>'+orders[i].dishNameList.length+'</td>';
		html+='<td>'+orders[i].totalValue+'</td>';
			
		html+='<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myOrder" onclick=setOrderID('+orders[i].order_id+')>Change Status</button></td>';
		html+='</tr>';
	}
	html += '</tbody></table>';

	$("#orders")[0].innerHTML=html;
//	alert(strToJson(data)[0].order_id);
}

function getAdminHistory() {
	var requestURI = "resource/order/searchHistory";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
			success: showAdminOrders,
		    error: addOrderFail
	};
	$.ajax(opts);
}

function showAdminOrders(data){
	orders = strToJson(data);
	gData=data;
	
	html ='<table class="table table-striped table-hover" >'+
	'<thead> <tr>'+
			'<th>#No</th><th>Order No</th><th>Booktime</th><th>Table</th><th>People</th><th>Dishs</th><th>Total</th></tr></thead>';
	
	html+='<tbody>';
	for(i=0;i<orders.length;i++) {
		html+='<tr>';
		html+='<td>'+(i+1)+'</td>';
		html+='<td>'+orders[i].orderNo+'</td>';
		html+='<td>'+orders[i].bookTime+'</td>';
		html+='<td>'+orders[i].tableName+'</td>';
		html+='<td>'+orders[i].numPeople+'</td>';
		html+='<td>'+orders[i].dishNameList.length+'</td>';
		html+='<td>'+orders[i].totalValue+'</td>';
		
		
		status = orders[i].status;
		if(status=="1") {
			status = "accepted";
		}
		else if ( status == "2") {
			status = "dismissed by user"
		}
		else if( status == "3" ) {
			status = "rejected";
		}
		else if(status == "4") {
			status = "finished";
		}
			
		html+='<td><button type="button" class="btn btn-primary btn-info" data-toggle="modal" data-target="#myOrder" onclick=setOrderID('+orders[i].order_id+')>'+status+'</button></td>';
		html+='</tr>';
	}
	html += '</tbody></table>';

	$("#el_psy_congroo")[0].innerHTML=html;
//	alert(strToJson(data)[0].order_id);
}


function getAssignTable(capacity,orderid) {
	ggord = orderid;
	var requestURI = "booktable/getAvailableTables?capacity="+capacity;
	var opts = {
	        type: "GET",
	        url:requestURI,
			success: showAvaTables,
		    error: addOrderFail
	};
	$.ajax(opts);
}

function showAvaTables(data) {
	
	tables = strToJson(data).returnValue;
	gtables = tables;
	html="";
	
	html +=  '<table class="table">' +
	'<thead>   <tr>      <th>#</th>      <th>Table</th>      <th>Capacity</th>  </tr><thead>';
	  
	html += '<tbody>'
    	
	for(i=0;i<tables.length;i++) {
		
		html += '<tr class="active"> <td scope="row">'+(i+1)+'</td><td>'+tables[i].number_code+'</td><td>'+tables[i].capacity+'</td><td><input type="radio" name="optionsRadios" value="tables_'+i+ '" id="tables_'+i+'"></td></tr>';
		
	}
	html += '</tbody></table>'
	
	$("#availableTables")[0].innerHTML=html;
	
	
}

function assignTabletoUser() {
	var obj = document.getElementsByName("optionsRadios");
	for(i=0;i<obj.length;i++) {
		if(obj[i].checked) {
			
			status = $("#selectstatus").val();
			var requestURI = "resource/order/modify";
			if ( status == "change to accepted" ) {
				var opts = {
				        type: "POST",
				        url:requestURI,
				        contentType: "application/json",
				        data:JSON.stringify({ "order_id": ggord,"user":{"userid":-1},"status":1,"tableName":gtables[i].number_code}), //admin -1; other people:userid
						success: updateAssignSubmitStatus,
					    error: addOrderFail
				};
			}
			else if (status == "change to rejected") {
				var opts = {
				        type: "POST",
				        url:requestURI,
				        contentType: "application/json",
				        data:JSON.stringify({ "order_id": ggord,"user":{"userid":-1},"status":3,"tableName":gtables[i].number_code}), //admin -1; other people:userid
						success: updateAssignSubmitStatus,
					    error: addOrderFail
				};
			}
			
			$.ajax(opts);
		}
	}
}



function addOrderFail(data){
//	alert(data);
}








