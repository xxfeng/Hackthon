package com.hackthon.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hackthon.base.DBConn;
import com.hackthon.domain.User;

public class UserDataPersistence {
	private final String selectUsers = "select firstname, lastname, username, password from user where username='%s'";
	
	public User findUser(String username)
	{
		DBConn instance = DBConn.getInstance();
		String sql = String.format(selectUsers, username);
		ResultSet res = instance.selectSQL(sql);
		if(res == null)
			return null;
		else
		{
			User user = new User();
			try {
				user.setFirstName(res.getString(0));
				user.setLastName(res.getString(1));
				user.setUsername(res.getString(2));
				user.setPassword(res.getString(3));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				user = null;
			}
			return user;
		}
	}

}
