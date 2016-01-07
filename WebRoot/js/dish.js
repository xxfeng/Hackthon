$(document).ready(function(){
	$("#queryDish").bind("click", searchDish);
});

function searchDish(){
	var requestURI = "resource/dish/search";
	var opts = {
	        type: "POST",
	        url:requestURI,
	        contentType: "application/json",
	        data:JSON.stringify({}),
//	        JSON.stringify({}),
			success: self.searchDishSuccess,
		    error: self.searchDishFail
	};
	$.ajax(opts);
}

function searchDishSuccess(data){
	
}

function searchDishFail(data){
	
}