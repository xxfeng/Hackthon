package com.hackthon.domain;

public class Dish {
	private String dish_id;
	private String name;
	private String price;
	private String picPath;
	private String numSale;
	private String discount;
	private String popular;
	private String type;
	
	public Dish(){
		
	}
	
	public Dish(String dish_id, 
				String name, 
				String price, 
				String picPath, 
				String  numSale, 
				String discount,
				String popular,
				String type){
		this.dish_id = dish_id;
		this.name = name;
		this.price = price;
		this.picPath = picPath;
		this.numSale = numSale;
		this.discount = discount;
		this.popular = popular;
		this.type = type;
			
	}
	
	public String getDish_id() {
		return dish_id;
	}

	public void setDish_id(String dish_id) {
		this.dish_id = dish_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getNumSale() {
		return numSale;
	}

	public void setNumSale(String numSale) {
		this.numSale = numSale;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPopular() {
		return popular;
	}

	public void setPopular(String popular) {
		this.popular = popular;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
