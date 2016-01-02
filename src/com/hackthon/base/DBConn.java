package com.hackthon.base;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	final static String  serverURL = "localhost"; 
	final static int serverPort = 3306;
	final static String schema = "Hackthon";
	final static String dbUserName = "root";
	final static String dbPass = "199010";
	
	private Connection conn = null;
	PreparedStatement statement = null;

	// connect to MySQL
	void connSQL() {
		String url = "jdbc:mysql://"+serverURL+":"+serverPort+"/"+schema+"?characterEncoding=UTF-8";
		// 加载驱动程序以连接数据库 
		try { 
			Class.forName("com.mysql.jdbc.Driver" ); 
			conn = DriverManager.getConnection( url,dbUserName, dbPass ); 
			}
		//捕获加载驱动程序异常
		 catch ( ClassNotFoundException cnfex ) {
			 System.err.println(
			 "装载 JDBC/ODBC 驱动程序失败。" );
			 cnfex.printStackTrace(); 
		 } 
		 //捕获连接数据库异常
		 catch ( SQLException sqlex ) {
			 System.err.println( "无法连接数据库" );
			 sqlex.printStackTrace(); 
		 }
	}

	// disconnect to MySQL
	void deconnSQL() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("关闭数据库问题 ：");
			e.printStackTrace();
		}
	}

	// execute selection language
	ResultSet selectSQL(String sql) {
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
	boolean insertSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}
		return false;
	}
	//execute delete language
	boolean deleteSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}
		return false;
	}
	//execute update language
	boolean updateSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}
		return false;
	}
	// show data in ju_users
	void layoutStyle2(ResultSet rs) {
		System.out.println("-----------------");
		System.out.println("执行结果如下所示:");
		System.out.println("-----------------");
		try {
			while (rs.next()) {
				System.out.println(rs.getString(1)+ "\t"
						+ rs.getString(2) + "\t"
						+ rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println("显示时数据库出错。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("显示出错。");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		DBConn conn = new DBConn();
		conn.connSQL();
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
		
		conn.deconnSQL();
	}
}
