package com.hackthon.webservice.Source;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackthon.domain.Dish;
import com.hackthon.domain.Order;

@Controller
public class DishController  extends BaseController{
	@RequestMapping(value="/dish/add", method=RequestMethod.POST)
    public @ResponseBody String add(@RequestBody Dish params) {
		
		//check field
		addDish(params);
        return "You can upload a file by posting to this same URL.";
    }

	@RequestMapping(value="/dish/search", method=RequestMethod.POST)
    public @ResponseBody List<Dish> search(@RequestBody Dish params){
		List<Dish> list = null;
		//by dish_id
		if(params.getDish_id() != null){
			list = getDishById(params.getDish_id());
			return list;
		}
		//by all
		list = getDishAll();
		return list;
	}


	///----------------------------------------------
	
	//-------------------------------
	private void addDish(Dish params) {
		// TODO Auto-generated method stub
		
	}
	
	private List<Dish> getDishById(String dish_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Dish> getDishAll() {
		// TODO Auto-generated method stub
		List<Dish> list = new ArrayList<Dish>();
		String rootPath = "image/dish/";
		//fake data
		list.add(new Dish("1","name_1", "price_1",rootPath+"1.jpg","numSale_1","discount_1", "popular_1","1"));
		list.add(new Dish("2","name_2", "price_2",rootPath+"2.jpg","numSale_2","discount_2", "popular_2","2"));
		list.add(new Dish("3","name_3", "price_3",rootPath+"3.jpg","numSale_3","discount_3", "popular_3","3"));
		list.add(new Dish("4","name_4", "price_4",rootPath+"4.jpg","numSale_4","discount_4", "popular_4","4"));
		list.add(new Dish("5","name_5", "price_5",rootPath+"5.jpg","numSale_5","discount_5", "popular_5","5"));
		list.add(new Dish("6","name_6", "price_6",rootPath+"6.jpg","numSale_6","discount_6", "popular_6","6"));
		return list;
	}
}
