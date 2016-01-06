package com.hackthon.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hackthon.base.DBConn;
import com.hackthon.domain.User;

public class UserDataPersistence {
	private final String selectUser = "select userid, firstname, lastname, username, password from user where username = ?";
	private final String insertUser = "insert into user (userid, firstname, lastname, username, password) values";
	public static final String COL_FNAME = "firstname";
	public static final String COL_LNAME = "lastname";
	public static final String COL_NAME = "username";
	public static final String COL_PSSWD = "password";
	public static final String COL_ID = "userid";
	public static final String ADMIN_NAME = "nicole.qian@oracle.com";
	public User findUser(String username)
	{
		DBConn instance = DBConn.getInstance();
		List<String> params = new ArrayList<String>();
		params.add(username);
		ResultSet res = instance.selectSQL(selectUser, params);
		User user = null;
		try {
			while(res.next()){
				user = new User();
				try {
					if(username.equals(ADMIN_NAME))
						user.setAdmin(true);
					user.setUserid(res.getInt(COL_ID));
					user.setFirstName(res.getString(COL_FNAME));
					user.setLastName(res.getString(COL_LNAME));
					user.setUsername(res.getString(COL_NAME));
					user.setPassword(res.getString(COL_PSSWD));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean insertUser(User user)
	{
		DBConn instance = DBConn.getInstance();
		List<String> params = new ArrayList<String>();
		params.add(user.getFirstName());
		params.add(user.getLastName());
		params.add(user.getUsername());
		params.add(user.getPassword());
		String sql = insertUser + "("+user.getUserid()+", "+ "?,?,?,?)";
		return instance.insertSQL(sql, params);
	}

}
