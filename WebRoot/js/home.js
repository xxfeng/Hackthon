$(document)
    .ready(
        function () {

            var shoppingCar = [];
            var pageResultArray=[];
            $(".menu-list li").click(function (e) {

                $(".menu-list li").removeClass("active");
                $("#sortdefault").click();
                $(this).addClass("active");
                if ($(this).text() == "famous dish") {
                    var dishtype = "1";
                    $('table tr td').remove();
                    filterMenu(dishtype, array,pageResultArray);
                } else if ($(this).text() == "cold-mix") {
                    var dishtype = "2";
                    $('table tr td').remove();
                    filterMenu(dishtype, array,pageResultArray);
                } else if ($(this).text() == "Hot Dishes") {
                    var dishtype = "3";
                    $('table tr td').remove();
                    filterMenu(dishtype, array,pageResultArray);

                } else if ($(this).text() == "Dimsum") {
                    var dishtype = "4";
                    $('table tr td').remove();
                    filterMenu(dishtype, array,pageResultArray);

                } else if ($(this).text() == "Staple") {
                    var dishtype = "5";
                    $('table tr td').remove();
                    filterMenu(dishtype, array,pageResultArray);

                } else if ($(this).text() == "Drinks") {
                    var dishtype = "6";
                    $('table tr td').remove();
                    filterMenu(dishtype, array,pageResultArray);
                } else if ($(this).text() == "All") {
                    var dishtype = "all";
                    $('table tr td').remove();
                    $('table tr td').remove();
                    filterMenu(dishtype, array,pageResultArray);
                }
            });
            $('.sort-on-menu span').click(function () {
                console.log("aa");
                $('.sort-on-menu span').removeClass('clickhighlight');
                $(this).addClass('clickhighlight');
                var filteredMenu=$('.menu-list').find('.active').text();
                var displaytype;
                switch(filteredMenu)
                {
                    case "famous dish":
                        displaytype="1";
                        break;
                    case "cold-mix":
                        displaytype="2";
                        break;
                    case "Hot Dishes":
                        displaytype="3";
                        break;
                    case "Dimsum":
                        displaytype="4";
                        break;
                    case "Staple":
                        displaytype="5";
                        break;
                    case "Drinks":
                        displaytype="6";
                        break;
                    case "All":
                        displaytype = "all";
                        break;
                }
               if (this.textContent == 'default') {
                    $('table tr td').remove();
                    var pageresult=filterMenu(displaytype,array,pageResultArray);
                } else if(this.textContent == 'sort by price ') {
                    var resultarray=[];
                    var pageresult=filterMenu(displaytype,array,pageResultArray)
                    resultarray=sortOnprice(pageresult);
                    $('table tr td').remove();
                    filterMenu("all",resultarray,pageresult)
                } else if (this.textContent == 'sort by sales ') {

                }
            })

            $(".cart-icon").live(
                "click",
                function () {
                    var displayDishName = $(
                        this.parentElement.parentElement).find(
                        '.dish-title').text();
                    displayDishName = displayDishName.substr(1);
                    // 遍历数组，取出对象
                    for (var index in array) {
                        if (array[index].name == displayDishName) {
                            var obj = array[index];
                            shoppingCar.push(obj);
                        }
                    }
                    reload_cart();
                    calculate_cart();

                });
            var array = [{
                "name": "name_1",
                "type": "1",
                "dish_id": "1",
                "price": "45",
                "picPath": "1.jpg",
                "numSale": "numSale_1",
                "discount": "discount_1",
                "popular": "popular_1"            }, {
                "name": "name_2",
                "type": "2",
                "dish_id": "2",
                "price": "55",
                "picPath": "2.jpg",
                "numSale": "numSale_2",
                "discount": "discount_2",
                "popular": "popular_2"
            }, {
                "name": "name_3",
                "type": "3",
                "dish_id": "3",
                "price": "66",
                "picPath": "3.jpg",
                "numSale": "numSale_3",
                "discount": "discount_3",
                "popular": "popular_3"
            }, {
                "name": "name_4",
                "type": "4",
                "dish_id": "4",
                "price": "77",
                "picPath": "4.jpg",
                "numSale": "numSale_4",
                "discount": "discount_4",
                "popular": "popular_4"
            }, {
                "name": "name_5",
                "type": "5",
                "dish_id": "5",
                "price": "50",
                "picPath": "5.jpg",
                "numSale": "numSale_5",
                "discount": "discount_5",
                "popular": "popular_5"
            }, {
                "name": "name_6",
                "type": "6",
                "dish_id": "6",
                "price": "41",
                "picPath": "6.jpg",
                "numSale": "numSale_6",
                "discount": "discount_6",
                "popular": "popular_6"
            }, {
                "name": "name_7",
                "type": "6",
                "dish_id": "6",
                "price": "40",
                "picPath": "6.jpg",
                "numSale": "numSale_6",
                "discount": "discount_6",
                "popular": "popular_6"
            }];

            // all dishes
            var rowNumber = 0;
            var dishNumber = 0;

            for (var index in array) {
                dishNumber = Number(index) + 1;
                var innerTd;
                innerTd = '<td><div class="dish-menu"><div class="menu-image"><img src="./lib/image/' + array[index].picPath + '" class="img-polaroid"></div> <div class="dish-information"> <div class="dish-title"> <div>'
                    + array[index].name
                    + '</div></div><div class="dish-price"><div style="display: inline" class="price"> $'
                    + array[index].price
                    + '</div><i class="fa fa-fw fa-shopping-cart cart-icon"  style="display: inline"></i></div></div></div></td>'

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

            function reload_cart() {
                $(".shop-cart .fa-trash-o").click();

                var cart_map = {};

                for (var i = 0; i < shoppingCar.length; i++) {
                    if (shoppingCar[i].name in cart_map) {
                        cart_map[shoppingCar[i].name][0] = cart_map[shoppingCar[i].name][0] + 1;
                    } else {
                        var each_item_info = [1, shoppingCar[i].price];
                        cart_map[shoppingCar[i].name] = each_item_info;
                    }
                }
            }

            function get_cart_json() {
                var cart_map = {};

                for (var i = 0; i < shoppingCar.length; i++) {
                    if (shoppingCar[i].name in cart_map) {
                        cart_map[shoppingCar[i].name][0] = cart_map[shoppingCar[i].name][0] + 1;
                    } else {
                        var each_item_info = [1, shoppingCar[i].price,
                            shoppingCar[i].picPath,shoppingCar[i].dish_id];
                        cart_map[shoppingCar[i].name] = each_item_info;
                    }
                }

                return cart_map;
            }

            function reload_cart() {
                $(".order-list .dishes-list").remove();

                var cart_map = get_cart_json();

                var new_cart_item = ""
                for (var each_item_info in cart_map) {
                    new_cart_item = new_cart_item
                        + '<div class="dishes-list" style="margin-top: 10px">\
                        <span style="display: inline;margin-left: 10px" class="each_item_info">'
                        + each_item_info
                        + '</span>\
						<div class="cell itemquantity" style="display: inline;margin-left: 20px">\
                            <i style="display: inline" class="fa fa-fw fa-minus-circle"></i>\
                            <input type="number" class="each_num" value="'
                        + cart_map[each_item_info][0]
                        + '" name="t1" style="width: 30px;margin-left: 3px"/>\
                            <i style="display: inline" class="fa fa-fw fa-plus-circle"></i>\
                        </div>\
                        <span style="margin-left: 10px">$</span>\
						<span class="cart_price">'
                        + cart_map[each_item_info][1]
                        + '</span>\
                        <span style="display: inline;margin-left: 20px">\
                            <i style="display: inline" class="fa fa-fw fa-trash-o"></i></span>\
						</div>'
                }

                $(".order-list").append(new_cart_item);

                calculate_cart();

                // alert(new_cart_item);
            }

            reload_cart();

            $(".shop-cart .fa-ban").live("click", function () {
                $(".order-list .dishes-list").remove();
                shoppingCar = []

                calculate_cart();

            })

            $(".shop-cart .fa-trash-o")
                .live(
                    "click",
                    function () {
                        $(this).closest(".dishes-list")
                            .remove();

                        var DishName = $(this).parent()
                            .siblings(".each_item_info")
                            .text();
                        for (var index = 0; index < shoppingCar.length; index++) {
                            if (shoppingCar[index].name == DishName) {
                                shoppingCar.splice(index, 1)
                                index = index - 1;
                            }
                        }

                        calculate_cart();
                    })

            $(".shop-cart .fa-plus-circle").live(
                "click",
                function () {

                    var DishName = $(this).parent().siblings(
                        ".each_item_info").text();
                    for (var index in array) {
                        if (array[index].name == DishName) {
                            var obj = array[index];
                            shoppingCar.push(obj);
                        }
                    }

                    num = parseInt($(this).prev("input").val());
                    $(this).prev("input").val(num + 1);

                    calculate_cart();

                })

            $(".shop-cart .fa-minus-circle").live(
                "click",
                function () {

                    var DishName = $(this).parent().siblings(
                        ".each_item_info").text();
                    for (var index in shoppingCar) {
                        if (shoppingCar[index].name == DishName) {
                            shoppingCar.splice(index, 1)
                            break;
                        }
                    }

                    num = parseInt($(this).next("input").val());

                    if (num == 1) {
                        $(this).closest(".dishes-list").remove();
                        calculate_cart();

                        return;
                    } else {
                        $(this).next("input").val(num - 1);
                    }

                    calculate_cart();

                })

            function calculate_cart() {
                var total_price = 0;

                $(".shop-cart .cart_price").each(
                    function () {
                        var each_num = $(this).siblings(
                            ".cell.itemquantity").children(
                            "input").val();
                        // .css("background-color", "red");
                        // alert(each_num);
                        total_price = total_price
                            + parseInt($(this).text())
                            * each_num;
                    })
                // alert(total_price);

                $(".shop-cart #total_price").text(total_price);

            }

            calculate_cart();

            $("#checkout_button")
                .click(
                    function () {

                        $("#checkout_tbody tr").remove();

                        var cart_map = get_cart_json();

                        i_count = 1;
                        for (var each_item in cart_map) {
                            var new_tr_node = '<tr>\
									<td>'
                                + i_count
                                + '</td>\
									<td>'
                                + each_item
                                + '</td>\
									<td>'
                                + cart_map[each_item][1]
                                + '</td>\
									<td>'
                                + cart_map[each_item][0]
                                + '</td>\
									<td>\
										<img src="'
                                + cart_map[each_item][1]
                                + '" alt="el_psy_congroo" class="meal_pic"/>\
									</td>\
								</tr>'
                            i_count++;

                            $("#checkout_tbody").append(
                                new_tr_node);

                        }

                        $("#checkout_price").text(
                            $(".shop-cart #total_price")
                                .text());

                    })

            $('#final_checkout').click(function () {
                var cart_map = get_cart_json();

                var dishNameList = []
                var dishNumList = []
                for (var each_item in cart_map) {

                    //var each_dishNameList ={};
                    //each_dishNameList["dish_id"] = cart_map[each_item][2];
                    //dishNameList.push(each_dishNameList);


                    dishNameList.push({"dish_id": cart_map[each_item][3]})
                    dishNumList.push(cart_map[each_item][0]);
                }

                var total_price = $(".shop-cart #total_price").text();
                var total_people = $("#people_val").val();
                var starttime = $("#starttime_val").val();
                var endtime = $("#endtime_val").val();
                var now_date = $("#date_val").val();

                //order_id:1
                //user:2

                addOrder(1, 2, dishNameList, dishNumList, total_price, total_people, starttime, endtime, now_date);

                $('#checkout').modal('hide');

                //$(".shop-cart .fa-ban").click();
            })

        });

function filterMenu(type, array,pageResultArray) {
    var rowNumber = 0;
    var dishNumber = 0;
     pageResultArray=[];

    for (var index in array)
        if (array[index].type == type) {
            pageResultArray.push(array[index]);
            dishNumber++;
            var innerTd;
            innerTd = '<td><div class="dish-menu"><div class="menu-image"><img src="./lib/image/' + array[index].picPath + '" class="img-polaroid"></div> <div class="dish-information"> <div class="dish-title"> <div>'
                + array[index].name
                + '</div></div><div class="dish-price"><div style="display: inline" class="price"> $'
                + array[index].price
                + '</div><i class="fa fa-fw fa-shopping-cart cart-icon"  style="display: inline"></i></div></div></div></td>'

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
        } else if (type == "all") {
            pageResultArray.push(array[index]);
            dishNumber++;
            var innerTd;
            innerTd = '<td><div class="dish-menu"><div class="menu-image"><img src="./lib/image/' + array[index].picPath + '" class="img-polaroid"></div> <div class="dish-information"> <div class="dish-title"> <div>'
                + array[index].name
                + '</div></div><div class="dish-price"><div style="display: inline" class="price"> $'
                + array[index].price
                + '</div><i class="fa fa-fw fa-shopping-cart cart-icon"  style="display: inline"></i></div></div></div></td>'

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
    return pageResultArray;
    }

function  sortOnprice(inputarray){
    var cpmpareArray=[];
    var sortedAarray=[];
    var sortedObj=[];
    for(var index in inputarray){
        cpmpareArray.push(inputarray[index].price)
    };
    cpmpareArray=cpmpareArray.sort(sortNumber);
    for (var index in cpmpareArray){
        for(var subindex in inputarray){
            if(cpmpareArray[index]==inputarray[subindex].price){
                sortedObj.push(inputarray[subindex]);
            }
        }
    }
    return sortedObj
}
function sortNumber(a,b)
{
    return a - b;
}