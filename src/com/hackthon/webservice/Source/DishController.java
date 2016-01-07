package com.hackthon.webservice.Source;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackthon.base.DBConn;
import com.hackthon.domain.Dish;
import com.hackthon.domain.Order;
import com.hackthon.representation.TableDataRepresentation;

@Controller
public class DishController  extends BaseController{
	@RequestMapping(value="/dish/add", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> add(@RequestBody Dish params) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		//check field
		if ( addDish(params) ) {
			returnMap.put("returnCode", 0);
			returnMap.put("returnMessage", "add dish success!");
		}
		else {
			returnMap.put("returnCode", 1);
			returnMap.put("returnMessage", "add dish failed!");
		}
		
        return returnMap;
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
	private boolean addDish(Dish params) {
		// TODO Auto-generated method stub
		DBConn conn = DBConn.getInstance();
		
		String sql = "insert into Hackthon.Dish(name,price,picPath,numSale,discount,popular,type) values('"+params.getName()+"','"+
		params.getPrice()+"','"+params.getPicPath()+"','"+params.getNumSale()+"','"+params.getDiscount()+"','"+params.getPopular()+"','"+params.getType()
		+"')";
		
		return conn.insertSQL(sql);
	
	}
	
	public List<Dish> getDishById(String dish_id) {
		// TODO Auto-generated method stub
		DBConn conn = DBConn.getInstance();
		String sql = "select dish_id,name,price,picPath,numSale,discount,popular,type from Hackthon.Dish where dish_id="+dish_id+";";
		
		List<Dish> list = new ArrayList<Dish>();
		
		ResultSet rs = conn.selectSQL(sql);
		
		try {
			while(rs.next()){
				 Dish dish = new Dish();
				 dish.setDish_id(dish_id);
				 dish.setName(rs.getString(2));
				 dish.setPrice(rs.getString(3));
				 dish.setPicPath(rs.getString(4));
				 dish.setNumSale(rs.getString(5));
				 dish.setDiscount(rs.getString(6));
				 dish.setPopular(rs.getString(7));	
				 dish.setType(rs.getString(8));
				 list.add(dish);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return list;
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
/*
		DBConn conn = DBConn.getInstance();
		String sql = "select dish_id,name,price,picPath,numSale,discount,popular,type from Hackthon.Dish order by name";
		
		List<Dish> list = new ArrayList<Dish>();
		
		ResultSet rs = conn.selectSQL(sql);
		
		try {
			while(rs.next()){
				 Dish dish = new Dish();
				 
				 dish.setName(rs.getString(1));
				 dish.setName(rs.getString(2));
				 dish.setPrice(rs.getString(3));
				 dish.setPicPath(rs.getString(4));
				 dish.setNumSale(rs.getString(5));
				 dish.setDiscount(rs.getString(6));
				 dish.setPopular(rs.getString(7));	
				 dish.setType(rs.getString(8));			 
				 list.add(dish);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		conn.destroy();
		*/
		return list;
	}
}
