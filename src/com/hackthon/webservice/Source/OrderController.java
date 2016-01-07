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
public class OrderController extends BaseController{
	
	@RequestMapping(value="/order/add", method=RequestMethod.POST)
    public @ResponseBody String add(@RequestBody Order params) {
//		System.out.println("order_id="+params.getOrder_id());
//		System.out.println("user.userid="+params.getUser().getUserid());
//		System.out.println("user.dishNamelist="+params.getDishNameList().get(0).getDish_id());
//		System.out.println("user.dishNumlist="+params.getDishNumList().get(0));
		addOrder(params);
        return "You can upload a file by posting to this same URL.";
    }
	
	@RequestMapping(value="/order/delete", method=RequestMethod.POST)
    public @ResponseBody String delete(@RequestBody Order params) {
		if(params.getOrder_id() == null){
			return this.NO_REQUEST_PARAMS;
		}
		// delete order sql
		deleteById(params.getOrder_id());
        return this.SUCCESS;
    }
	
	@RequestMapping(value="/order/modify", method=RequestMethod.POST)
    public @ResponseBody String modify(@RequestBody Order params) {
		if(params.getOrder_id() == null){
			return this.NO_REQUEST_PARAMS;
		}
		//update sql
		updateOrderById(params);
		return this.SUCCESS;
    }

	@RequestMapping(value="/order/search", method=RequestMethod.POST)
    public @ResponseBody List<Order> search(@RequestBody Order params){
		//by order_id
		List<Order> orderList = null;
		if(params.getOrder_id()!=null){
			orderList = searchByOrderId(params);
			return orderList;
		}
		//by user_id  -1 all user
		if(params.getUser() !=null && params.getUser().getUserid() != null && params.getStatus() == null){
			Integer userid = params.getUser().getUserid();
			if(userid == -1){ //admin
				orderList = getOrderByAdmin(params);
			}else{
				orderList = getOrderByUser(params);
			}
			return orderList;
		}
		//by user_id and status
		if(params.getUser() !=null && params.getUser().getUserid() != null && params.getStatus() != null){
			Integer userid = params.getUser().getUserid();
			if(userid == -1){ //admin
				orderList = getOrderByAdmin(params);
			}else{
				orderList = getOrderByUser(params);
			}
			String status = params.getStatus();
			//filter Order
			orderList = filterOrderByStatus(status, orderList);
		}
		if(orderList == null)
			orderList = new ArrayList<Order>();
		return orderList;
	}

	private void addOrder(Order params) {
		// TODO Auto-generated method stub
		
	}

	private void deleteById(String order_id) {
		// TODO Auto-generated method stub
		
	}
	private List<Order> getOrderByUser(Order params) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Order> getOrderByAdmin(Order params) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Order> searchByOrderId(Order params) {
		// TODO Auto-generated method stub
		List<Order> orderList = new ArrayList<Order>();
		Order order = new Order("order_id", "orderNo");
		List<Dish> list = getDishByOrderId(params.getOrder_id());
		order.setDishNameList(list);
		orderList.add(order);
		return orderList;
	}
	
	private void updateOrderById(Order params) {
		// TODO Auto-generated method stub
		
	}
	
	private List<Order> filterOrderByStatus(String status, List<Order> orderList) {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();
		for(int i=0, size= orderList!=null?orderList.size():0; i<size; i++){
			if(orderList.get(i).getStatus().equals(status)){
				list.add(orderList.get(i));
			}
		}
		return list;
	}

	private List<Dish> getDishByOrderId(String order_id){
		//fake data
		String rootPath = "image/dish/";
		List<Dish> list = new ArrayList<Dish>();
		list.add(new Dish("1","name_1", "price_1",rootPath+"1.jpg","numSale_1","discount_1", "popular_1","1"));
		list.add(new Dish("2","name_2", "price_2",rootPath+"2.jpg","numSale_2","discount_2", "popular_2","2"));
		list.add(new Dish("3","name_3", "price_3",rootPath+"3.jpg","numSale_3","discount_3", "popular_3","3"));
		list.add(new Dish("4","name_4", "price_4",rootPath+"4.jpg","numSale_4","discount_4", "popular_4","4"));
		list.add(new Dish("5","name_5", "price_5",rootPath+"5.jpg","numSale_5","discount_5", "popular_5","5"));
		list.add(new Dish("6","name_6", "price_6",rootPath+"6.jpg","numSale_6","discount_6", "popular_6","6"));

		return list;
		
	}
}
