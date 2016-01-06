package com.hackthon.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DBConn {

	final static String  serverURL = "198.11.176.153"; 
	final static int serverPort = 3306;
	final static String schema = "Hackthon";
	final static String dbUserName = "root";
	final static String dbPass = "password";
	//final static String dbPass = "199010";
	
	private Connection conn = null;
	private static DBConn instance = null;
	PreparedStatement statement = null;
	private DBConn(){
		
	}
	
	/*
	 * return db instance
	 */
    public static DBConn getInstance()
    {
    	if(instance == null)
    	{
    		instance = new DBConn();
    		instance.connSQL();
    	}
    	return instance;
    }
    
    public void destroy()
    {
    	instance.deconnSQL();
    	instance = null;
    }
    
	// connect to MySQL
	private void connSQL() {
		String url = "jdbc:mysql://"+serverURL+":"+serverPort+"/"+schema+"?characterEncoding=UTF-8";
		try { 
			Class.forName("com.mysql.jdbc.Driver" ); 
			conn = DriverManager.getConnection( url,dbUserName, dbPass ); 
			}
		 catch ( ClassNotFoundException cnfex ) {
			 System.err.println(
			 "jdbc driver is not installed" );
			 cnfex.printStackTrace(); 
		 } 
		 catch ( SQLException sqlex ) {
			 System.err.println( "failed to connect mysql" );
			 sqlex.printStackTrace(); 
		 }
	}

	// disconnect to MySQL
	void deconnSQL() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("failed to disconnect mysql");
			e.printStackTrace();
		}
	}

	// execute selection language
	public ResultSet selectSQL(String sql) {
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// execute insertion language
	public boolean insertSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("failed to execute sql statement");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("failed to insert data.");
			e.printStackTrace();
		}
		return false;
	}
	//execute delete language
	public boolean deleteSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("failed to execute sql statment");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("failed to delete data.");
			e.printStackTrace();
		}
		return false;
	}
	//execute update language
	public boolean updateSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("failed to execute sql statement");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("failed to update data");
			e.printStackTrace();
		}
		return false;
	}
	
	//tackle parameters
	public ResultSet selectSQL(String sql, Map<String, Object> params)
	{
		ResultSet res = null;
		try {
			statement = conn.prepareStatement(sql);
			int index = 1;
			for( String key : params.keySet())
			{
				statement.setObject(index, params.get(key));
				index ++;
			}
		    res = statement.executeQuery();
		} catch (SQLException e) {
			System.out.println("failed to execute sql statement");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("failed to select data");
			e.printStackTrace();
		}
		return res;
	}
	
	//tackle parameters
	public boolean insertSQL(String sql, Map<String, Object> params)
	{
		try{
			statement = conn.prepareStatement(sql);
			int index = 1;
			for( String key : params.keySet())
			{
				statement.setObject(index, params.get(key));
				index ++;
			}
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("failed to execute sql statement");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("failed to insert data");
			e.printStackTrace();
		}
		return false;
	}
	
	// show data in ju_users
	void layoutStyle2(ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.println(rs.getString(1)+ "\t"
						+ rs.getString(2) + "\t"
						+ rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println("failed to execute sql statement");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("failed to test data");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		DBConn conn = DBConn.getInstance();
		String s = "select * from User";

		String insert = "insert into User(username,password,status) values('Andy','123456','active')";
		String update = "update User set password ='199000' where username= 'Andy'";
		String delete = "delete from User where username= 'mm'";

		if (conn.insertSQL(insert) == true) {
			System.out.println("insert successfully");
			ResultSet resultSet = conn.selectSQL(s);
			conn.layoutStyle2(resultSet);
		}
		if (conn.updateSQL(update) == true) {
			System.out.println("update successfully");
			ResultSet resultSet = conn.selectSQL(s);	
			conn.layoutStyle2(resultSet);
		}
		if (conn.insertSQL(delete) == true) {
			System.out.println("delete successfully");
			ResultSet resultSet = conn.selectSQL(s);
			conn.layoutStyle2(resultSet);
		}
		conn.destroy();
	}
}
