package com.hackthon.webservice.Source;

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
		return null;
	}
}
