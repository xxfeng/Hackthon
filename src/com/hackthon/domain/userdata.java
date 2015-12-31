package com.hackthon.domain;
/**
 *@author 作者 Email:xiangping165@sina.com
 *@version 创建时间：2013-12-15下午10:05:22
 *@类说明 用户属性
 */

public class userdata {
	//用户名
	private String username;
	//密码
	private String password;
	//用户创建时间
	private String createTime;
	//更新时间
	private String  updateTime;
	//姓
	private String firstName;
	//名
	private String secondName;
	//邮箱
	private String email;
	//类型
	private String type;
	//用来登录的token值
	private String token;
	private long tokenCreateTime;
	//token值得有效期
	private long Expires;
	//是否可用
	private int isAvailable;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getExpires() {
		return Expires;
	}
	public void setExpires(long expires) {
		Expires = expires;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public long getTokenCreateTime() {
		return tokenCreateTime;
	}
	public void setTokenCreateTime(long tokenCreateTime) {
		this.tokenCreateTime = tokenCreateTime;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	

}
