package com.hackthon.representation;

public class TableDataRepresentation {
	int id;  //流水
	String number_code;  //编号
	String status;   //状态
	int capacity;  //可容纳人数
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber_code() {
		return number_code;
	}
	public void setNumber_code(String number_code) {
		this.number_code = number_code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
