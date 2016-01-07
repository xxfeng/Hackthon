$(document).ready(function () {

    var shoppingCar = [];
    $(".menu-list li").click(function (e) {

        $(".menu-list li").removeClass("active");
        $(this).addClass("active");
        if ($(this).text() == "famous dish") {
            var dishtype = "1";
            $('table tr td').remove();
            filterMenu(dishtype, array);
        }
        else if ($(this).text() == "cold-mix") {
            var dishtype = "2";
            $('table tr td').remove();
            filterMenu(dishtype, array);
        }
        else if ($(this).text() == "Hot Dishes") {
            var dishtype = "3";
            $('table tr td').remove();
            filterMenu(dishtype, array);

        }
        else if ($(this).text() == "Dimsum") {
            var dishtype = "4";
            $('table tr td').remove();
            filterMenu(dishtype, array);

        } else if ($(this).text() == "Staple") {
            var dishtype = "5";
            $('table tr td').remove();
            filterMenu(dishtype, array);

        } else if ($(this).text() == "Drinks") {
            var dishtype = "6";
            $('table tr td').remove();
            filterMenu(dishtype, array);
        }
        else  if($(this).text() == "All"){
            var dishtype = "all";
            $('table tr td').remove();
            $('table tr td').remove();
            filterMenu(dishtype, array);
        }
    });

    $(".cart-icon").live("click", function () {
        var displayDishName = $(this.parentElement.parentElement).find('.dish-title').text();
        displayDishName=displayDishName.substr(1);
        //遍历数组，取出对象
        for(var index in array){
            if(array[index].name==displayDishName){
                var obj=array[index];
                shoppingCar.push(obj);
            }
        }
		
		reload_cart();
		
    });
    var array = [{
        "name": "name_1",
        "type": "1",
        "picPath": "example.jpg",
        "price": "55",
        "numSale": "numSale_1",
        "discount": "discount_1",
        "popular": "popular_1",
        "dish_id": "1"
    },
        {
            "name": "name_2",
            "type": "2",
            "picPath": "example.jpg",
            "price": "55",
            "numSale": "numSale_1",
            "discount": "discount_1",
            "popular": "popular_1",
            "dish_id": "2"
        },
        {
            "name": "name_3",
            "type": "3",
            "picPath": "example.jpg",
            "price": "45",
            "numSale": "numSale_1",
            "discount": "discount_1",
            "popular": "popular_1",
            "dish_id": "3"
        },
        {
            "name": "name_4",
            "type": "4",
            "picPath": "example.jpg",
            "price": "35",
            "numSale": "numSale_1",
            "discount": "discount_1",
            "popular": "popular_1",
            "dish_id": "4"
        },
        {
            "name": "name_5",
            "type": "5",
            "picPath": "example.jpg",
            "price": "35",
            "numSale": "numSale_1",
            "discount": "discount_1",
            "popular": "popular_1",
            "dish_id": "5"
        },
        {
            "name": "name_6",
            "type": "6",
            "picPath": "example.jpg",
            "price": "35",
            "numSale": "numSale_1",
            "discount": "discount_1",
            "popular": "popular_1",
            "dish_id": "6"
        },
        {
            "name": "name_1",
            "type": "1",
            "picPath": "example.jpg",
            "price": "55",
            "numSale": "numSale_1",
            "discount": "discount_1",
            "popular": "popular_1",
            "dish_id": "1"
        },
        {
            "name": "name_4",
            "type": "4",
            "picPath": "example.jpg",
            "price": "35",
            "numSale": "numSale_1",
            "discount": "discount_1",
            "popular": "popular_1",
            "dish_id": "4"
        },
    ];

    //all dishes
    var rowNumber = 0;
    var dishNumber = 0;

        for (var index in array) {
            dishNumber = Number(index) + 1;
            var innerTd;
            innerTd = '<td><div class="dish-menu"><div class="menu-image"><img src="./lib/image/example.jpg" class="img-polaroid"></div> <div class="dish-information"> <div class="dish-title"> <div>' + array[index].name + '</div></div><div class="dish-price"><div style="display: inline" class="price"> $' + array[index].price + '</div><i class="fa fa-fw fa-shopping-cart cart-icon"  style="display: inline"></i></div></div></div></td>'

            if (rowNumber == 0) {
                $(innerTd).appendTo("#first-row");
            } else if (rowNumber == 1) {
                $(innerTd).appendTo("#second-row");

            } else if (rowNumber == 2) {
                $(innerTd).appendTo("#third-row");

            } else if (rowNumber == 3) {
                $(innerTd).appendTo("#forth-row");
            }
            if (dishNumber % 3 === 0) {
                rowNumber == rowNumber++;
            }
        }
	
	function reload_cart()
	{
		$(".shop-cart .fa-trash-o").click();
		
		var cart_map = {};
		
		for(var i=0;i<shoppingCar.length;i++)
		{
			if(shoppingCar[i].name in cart_map)
			{
				cart_map[shoppingCar[i].name][0] = cart_map[shoppingCar[i].name][0] + 1;
			}
			else
			{
				var each_item_info = [1,shoppingCar[i].price];
				cart_map[shoppingCar[i].name] = each_item_info;
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
		
		if(num==1)
		{
			$(".shop-cart .fa-trash-o").click();
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
function filterMenu(type, array) {
    var rowNumber = 0;
    var dishNumber = 0;

    for (var index in array) {
        if (array[index].type == type) {
            dishNumber = Number(index) + 1;
            var innerTd;
            innerTd = '<td><div class="dish-menu"><div class="menu-image"><img src="./lib/image/example.jpg" class="img-polaroid"></div> <div class="dish-information"> <div class="dish-title"> <div>' + array[index].name + '</div></div><div class="dish-price"><div style="display: inline" class="price"> $' + array[index].price + '</div><i class="fa fa-fw fa-shopping-cart cart-icon"  style="display: inline"></i></div></div></div></td>'

            if (rowNumber == 0) {
                $(innerTd).appendTo("#first-row");
            } else if (rowNumber == 1) {
                $(innerTd).appendTo("#second-row");

            } else if (rowNumber == 2) {
                $(innerTd).appendTo("#third-row");

            } else if (rowNumber == 3) {
                $(innerTd).appendTo("#forth-row");
            }
            if (dishNumber % 3 === 0) {
                rowNumber == rowNumber++;
            }
        }
        else if(type=="all"){
            dishNumber = Number(index) + 1;
            var innerTd;
            innerTd = '<td><div class="dish-menu"><div class="menu-image"><img src="./lib/image/example.jpg" class="img-polaroid"></div> <div class="dish-information"> <div class="dish-title"> <div>' + array[index].name + '</div></div><div class="dish-price"><div style="display: inline" class="price"> $' + array[index].price + '</div><i class="fa fa-fw fa-shopping-cart cart-icon"  style="display: inline"></i></div></div></div></td>'

            if (rowNumber == 0) {
                $(innerTd).appendTo("#first-row");
            } else if (rowNumber == 1) {
                $(innerTd).appendTo("#second-row");

            } else if (rowNumber == 2) {
                $(innerTd).appendTo("#third-row");

            } else if (rowNumber == 3) {
                $(innerTd).appendTo("#forth-row");
            }
            if (dishNumber % 3 === 0) {
                rowNumber == rowNumber++;
            }
        }
    }
}
