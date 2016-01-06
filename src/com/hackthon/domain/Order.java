package com.hackthon.domain;


import java.util.List;

public class Order {
	private String order_id;
	private String orderNo;
	private User user;
	private String numPeople;
	
//	private Map<String, String> dishList;
	private List<Dish> dishNameList;
	private List<String> dishNumList;

	private String totalValue;
	private String bookTime;
	private String dinnerTime;
	private String checkTime;
	private String status;
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*public Map<String, String> getDishList() {
		return dishList;
	}
	public void setDishList(Map<String, String> dishList) {
		this.dishList = dishList;
	}*/
	
	public String getNumPeople() {
		return numPeople;
	}
	public List<String> getDishNumList() {
		return dishNumList;
	}
	public void setDishNumList(List<String> dishNumList) {
		this.dishNumList = dishNumList;
	}
	public void setNumPeople(String numPeople) {
		this.numPeople = numPeople;
	}
	public String getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	public String getBookTime() {
		return bookTime;
	}
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	public String getDinnerTime() {
		return dinnerTime;
	}
	public void setDinnerTime(String dinnerTime) {
		this.dinnerTime = dinnerTime;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public List<Dish> getDishNameList() {
		return dishNameList;
	}
	public void setDishNameList(List<Dish> dishNameList) {
		this.dishNameList = dishNameList;
	}
	
	
}
