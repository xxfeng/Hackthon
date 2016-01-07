$(document).ready(function(){

    $(".menu-list li").click(function(e){
        $(".menu-list li").removeClass("active");
        $(this).addClass("active");
    });

    $('#myTab a:last').tab('show');
	

	var array = [{"name":"name_1","type":"1","picPath":"image/dish/1.jpg","price":"55","numSale":"numSale_1","discount":"discount_1","popular":"popular_1","dish_id":"1"}];
	
	
	function reload_cart()
	{
		var cart_map = {};
		
		for(var i=0;i<array.length;i++)
		{
			if(array.name in cart_map)
			{
				cart_map[array[i].name][0] = cart_map[array[i].name][0] + 1;
			}
			else
			{
				var each_item_info = [1,array[i].price];
				cart_map[array[i].name] = each_item_info;
			}
		}
		
		
		var new_cart_item=""
		
		for(var each_item_info in cart_map)
		{
			new_cart_item = new_cart_item+ '<div class="dishes-list" style="margin-top: 10px">\
                        <span style="display: inline;margin-left: 10px">'+each_item_info +'</span>\
						<div class="cell itemquantity" style="display: inline;margin-left: 20px">\
                            <i style="display: inline" class="fa fa-fw fa-minus-circle"></i>\
                            <input type="number" class="each_num" value="'+cart_map[each_item_info][0] +'" name="t1" style="width: 30px;margin-left: 3px"/>\
                            <i style="display: inline" class="fa fa-fw fa-plus-circle"></i>\
                        </div>\
                        <span style="margin-left: 10px">$</span>\
						<span class="cart_price">'+cart_map[each_item_info][1]+'</span>\
                        <span style="display: inline;margin-left: 20px">\
                            <i style="display: inline" class="fa fa-fw fa-trash-o"></i></span>\
						</div>'	
		}
		
		$(".order-list").append(new_cart_item);
		
		
		//alert(new_cart_item);	
	}
	
	
	reload_cart();

	$(".shop-cart .fa-ban").live("click",function(){
		$(".order-list .dishes-list").remove();
		
		calculate_cart();
		
	})	
	
	$(".shop-cart .fa-trash-o").live("click",function(){
		$(this).closest(".dishes-list").remove();
		
		calculate_cart();
	})	
	
	$(".shop-cart .fa-plus-circle").live("click",function(){
		
		num = parseInt($(this).prev("input").val());
		$(this).prev("input").val(num+1);
		
		calculate_cart();
		
	})	
	
	$(".shop-cart .fa-minus-circle").live("click",function(){
		
		num = parseInt($(this).next("input").val());
		
		if(num==0)
		{
				return;
		}
		else
		{
			$(this).next("input").val(num-1);
		}
		
		
		calculate_cart();
		
	})		
	
	
	function calculate_cart()
	{
		var total_price = 0;
		
		$(".shop-cart .cart_price").each(function(){
				var each_num = $(this).siblings(".cell.itemquantity").children("input").val();
				//.css("background-color", "red");
				//alert(each_num);
				total_price = total_price + parseInt($(this).text()) * each_num ;
		})
		//alert(total_price);
		
		$(".shop-cart #total_price").text(total_price);
		
	}
	
	
	calculate_cart();
});
