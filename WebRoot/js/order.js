$(document).ready(function(){
	$("#addOrder").bind("click", addOrder);
	$("#searchOrderByOrderId").bind("click", searchOrderByOrderId);
	$("#searchOrderByUser").bind("click", searchOrderByUser);
	$("#searchOrderByStatus").bind("click", searchOrderByStatus);
});



var gData = null;


function strToJson(str){ 
	var json = eval('(' + str + ')'); 
	return json; 
}

function addOrder(order_id,user,dishNameList,dishNumList,totalValue,totalPeople){
	
	var stringJson = {}
	
	stringJson["order_id"] = order_id;
	stringJson["user"] = {"userid":user};
	stringJson["dishNameList"] = dishNameList;
	stringJson["dishNumList"] = dishNumList;
	stringJson["totalValue"] = totalValue;
	stringJson["totalPeople"] = totalPeople;
	
	var requestURI = "resource/order/add";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
	        data:JSON.stringify(stringJson),
			success: addOrderSuccess,
		    error: addOrderFail
	};
	$.ajax(opts);
}


function addOrderSuccess(data)
{
	bootbox.alert("Submit successful");
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


function searchFirst() {
	searchOrderByUser();
}

//------------------------------------------------------------
function getOrderSuccess(data){
	
	orders = strToJson(data);
	var changedOrders = new Array();
	//check if new status changed
	if(gData!=null) {
		neworders = orders;
		oldorders = strToJson(gData);

		for(i=0;i<oldorders.length;i++) {
			flag=0;
			for(j=0;j<neworders.length;j++) {
				if( oldorders[i].order_id == neworders[j].order_id ) {
					if( oldorders[i].status != neworders[j].status )
						changedOrders.push(j);
				}
			}
		}
		
		if( changedOrders.length>0) {
			alert("Exists Order Status Changed, please check!");
		}
		else
			return;
		
	}
	
	gData=data;
	
	html ='<table class="table" >'+
	'<thead> <tr>'+
			'<th>#No</th><th>Order No</th><th>Booktime</th><th>Table</th><th>People</th><th>Dishs</th><th>Total</th><th>Status</th></tr></thead>';
	
	html+='<tbody>';
	
	for(i=0;i<orders.length;i++) {
		cstr = '<tr>';
		for(j=0;j<changedOrders.length;j++) {
			if(changedOrders[j]==i) {
				cstr = '<tr style="background-color:bisque">';
				break;
			}
		}
			
		
		html+= cstr;
		
		html+='<td>'+(i+1)+'</td>';
		html+='<td>'+orders[i].orderNo+'</td>';
		html+='<td>'+orders[i].bookTime+'</td>';
		if(null != orders[i].tableName && orders[i].tableName.length>0)
			html+='<td>'+orders[i].tableName+'</td>';
		else
			html+='<td>wait for assign</td>';
		html+='<td>'+orders[i].numPeople+'</td>';
		html+='<td>'+orders[i].dishNameList.length+'</td>';
		html+='<td>'+orders[i].totalValue+'</td>';
		html+='<td>'+orders[i].status+'</td>';
		html+='<td><button type="button" class="btn btn-primary btn-small" style="margin-top: -5px" data-toggle="modal" data-target="#myModal" onclick=showDishs("'+i+'")>Details</button></td>';
		html+='</tr>';
	}
	html += '</tbody></table>';

	$("#orderlist")[0].innerHTML=html;
//	alert(strToJson(data)[0].order_id);
}

function addOrderFail(data){
//	alert(data);
}






