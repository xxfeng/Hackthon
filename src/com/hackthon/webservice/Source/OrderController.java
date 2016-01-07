package com.hackthon.webservice.Source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackthon.base.DBConn;
import com.hackthon.domain.Dish;
import com.hackthon.domain.Order;


@Controller
public class OrderController extends BaseController{
	public static final String ORDER_TABLE = "Order";
	@RequestMapping(value="/order/add", method=RequestMethod.POST)
    public @ResponseBody String add(@RequestBody Order order) {
//		System.out.println("order_id="+params.getOrder_id());
//		System.out.println("user.userid="+params.getUser().getUserid());
//		System.out.println("user.dishNamelist="+params.getDishNameList().get(0).getDish_id());
//		System.out.println("user.dishNumlist="+params.getDishNumList().get(0));
		addOrder(order);
        return "You can upload a file by posting to this same URL.";
    }
	
	@RequestMapping(value="/order/delete", method=RequestMethod.POST)
    public @ResponseBody String delete(@RequestBody Order params) {
		if(params.getOrder_id() == null){
			return this.NO_REQUEST_PARAMS;
		}
		// delete order sql
		try{
			deleteById(params.getOrder_id());
		}catch(Exception err)
		{
			System.out.println(err.getMessage());
		}
        return this.SUCCESS;
    }
	
	@RequestMapping(value="/order/modify", method=RequestMethod.POST)
    public @ResponseBody String modify(@RequestBody Order order) {
		if(order.getOrder_id() == null){
			return this.NO_REQUEST_PARAMS;
		}
		//update sql
		try {
			updateOrderById(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		return orderList;
	}

	private void addOrder(Order params) {
		// TODO Auto-generated method stub
		DBConn conn = DBConn.getInstance();
		
		String orderNo = UUID.randomUUID().toString();
		
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String systime = dateFormat.format( now ); 
		
		String sql = "insert into Hackthon.Order(orderNo,user_id,numPeople,dishNameList,dishNumlist,totalValue,bookTime,dinnerTime,checkTime,status) values('"+
				orderNo+"','"+params.getUser().getUserid()+"','"+params.getNumPeople()+"','"+params.getDishNameList()+"','"+
				params.getDishNumList()+"','"+params.getTotalValue()+"','"+systime+"','"+params.getDinnerTime()+"','"+params.getCheckTime()+"','0')";
		
		if (conn.insertSQL(sql)) {
			System.out.println("Order Create success!");
		}
		else {
			System.out.println("Order Create failed!");
		}	
	}

	private void deleteById(String order_id)throws Exception {
		DBConn conn = DBConn.getInstance();
		String sql = "delete from " + ORDER_TABLE + "where order_id = '" +order_id+ "'";
		if(!conn.deleteSQL(sql))
			throw new Exception("can not delete order");
	    	
	}
	private List<Order> getOrderByUser(Order params) {
		// TODO Auto-generated method stub
		DBConn conn = DBConn.getInstance();
		List<Order> list = new ArrayList<Order>();
		
		String sql = "select order_id,orderNo,numPeople,dishNameList,dishNumlist,totalValue,bookTime,dinnerTime,checkTime,status "+
		             "from Hackthon.Order where user_id='"+params.getUser().getUserid()+"';";
		
		ResultSet rs = conn.selectSQL(sql);
		
		try {
			while(rs.next()){
				 Order order = new Order();
				 order.setOrder_id(rs.getString(1));
				 order.setOrderNo(rs.getString(2));
				 order.setNumPeople(rs.getString(3));
				 order.setDishNameList(convertStringToDish(rs.getString(4)));
				 order.setDishNumList(Arrays.asList( rs.getString(5).split(",") ));
				 order.setTotalValue(rs.getString(6));
				 order.setBookTime(rs.getString(7));
				 order.setDinnerTime(rs.getString(8));
				 order.setCheckTime(rs.getString(9));
				 order.setStatus(rs.getString(10));	 
				 list.add(order);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return list;
	}

	private List<Order> getOrderByAdmin(Order params) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Order> searchByOrderId(Order params) {
		// TODO Auto-generated method stub
		getDishByOrderId(params.getOrder_id());
		return null;
	}
	
	private void updateOrderById(Order order) throws Exception {
		DBConn conn = DBConn.getInstance();
		String sql = "update Hackthon.Order set status='"+order.getStatus()+"' where order_id='"+order.getOrder_id()+"'";
		if(!conn.updateSQL(sql))
			throw new Exception("can not update order");
	}
	
	private List<Order> filterOrderByStatus(String status, List<Order> orderList) {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();
		for(int i=0, size=orderList.size(); i<size; i++){
			if(orderList.get(i).getStatus().equals(status)){
				list.add(orderList.get(i));
			}
		}
		return list;
	}

	private List<Dish> getDishByOrderId(String order_id){
		return null;
		
	}
	
	//dish_id
	public List<Dish> convertStringToDish(String listName) {
		List<Dish> list = new ArrayList<Dish>();
		String[] ids = listName.split(",");
		
		for(int i=0;i<ids.length;i++) {
			DishController dc = new  DishController();
			List<Dish> dish = dc.getDishById(ids[i]);
			list.add(dish.get(0));
		}
		return list;
	}
	
}
