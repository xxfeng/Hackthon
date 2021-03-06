package com.hackthon.representation;

public class UserDataRepresentation {
	String username; //email
	String password;
	String firstname;
	String lastname;
	boolean isAdmin;
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public UserDataRepresentation() {
		
	}
	
	public UserDataRepresentation(String username, String password, String firstname, String lastname, boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isAdmin = isAdmin;
	}
}
