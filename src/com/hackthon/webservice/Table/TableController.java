package com.hackthon.webservice.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hackthon.base.DBConn;
import com.hackthon.representation.TableDataRepresentation;


@Controller
public class TableController {
	static String AVAILABLE = "AVAILABLE"; 
	static String OCCUPIED = "OCCUPIED";
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addTable(
			@RequestParam("number_code") String number_code,
			@RequestParam("capacity") int capacity
	) 
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		DBConn conn = DBConn.getInstance();
		String sql = "insert into bookTable(number_code,capacity) values("+number_code+","+capacity+");";
		
		if( conn.insertSQL(sql) ) {
			returnMap.put("returnCode", 0);
			returnMap.put("returnMessage", "table add success!");
		}
		else {
			returnMap.put("returnCode", 1);
			returnMap.put("returnMessage", "Failed,please try again!");
		}
		conn.destroy();
		return returnMap;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delTableByNumberCode(
			@RequestParam("number_code") String number_code
	)
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		DBConn conn = DBConn.getInstance();
		String sql = "delete from bookTable where number_code='"+number_code+"';";
		
		if( conn.deleteSQL(sql) ) {
			returnMap.put("returnCode", 0);
			returnMap.put("returnMessage", "table delete success!");
		}
		else {
			returnMap.put("returnCode", 1);
			returnMap.put("returnMessage", "Failed,please try again!");
		}
		conn.destroy();
		return returnMap;
	}
	
	/*
	 * order by number_code
	 */
	@RequestMapping("/getAllTables")
	@ResponseBody
	public Map<String, Object> getAllTables( )
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		DBConn conn = DBConn.getInstance();
		String sql = "select number_code,capacity,status from bookTable order by number_code asc;";
		List<TableDataRepresentation> list = new ArrayList<TableDataRepresentation>();
		
		ResultSet rs = conn.selectSQL(sql);
		
		try {
			while(rs.next()){
				 TableDataRepresentation table = new TableDataRepresentation();
				 table.setNumber_code(rs.getString(1));
				 table.setCapacity(rs.getInt(2));
				 table.setStatus(rs.getString(3));
				 list.add(table);
			}
			
			returnMap.put("returnCode", 0);
			returnMap.put("returnValue", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			returnMap.put("returnCode", 1);
			returnMap.put("returnMessage", e.getMessage());
		}
		
		return returnMap;
	}
	
	@RequestMapping("/getAvailableTables")
	@ResponseBody
	public Map<String, Object> getAvailableTables(@RequestParam("capacity") int capacity )
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		DBConn conn = DBConn.getInstance();
		String sql = null;
		if ( capacity == 0 )
			sql = "select number_code,capacity from bookTable where status='AVAILABLE' order by number_code asc;";
		else
			sql = "select number_code,capacity from bookTable where status='AVAILABLE' and capacity>="+capacity+" order by number_code,capacity asc;";
		
		List<TableDataRepresentation> list = new ArrayList<TableDataRepresentation>();
		
		ResultSet rs = conn.selectSQL(sql);
		
		try {
			while(rs.next()){
				 TableDataRepresentation table = new TableDataRepresentation();
				 table.setNumber_code(rs.getString(1));
				 table.setCapacity(rs.getInt(2));
				 table.setStatus(rs.getString(3));
				 list.add(table);
			}
			
			returnMap.put("returnCode", 0);
			returnMap.put("returnValue", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			returnMap.put("returnCode", 1);
			returnMap.put("returnMessage", e.getMessage());
		}
		
		conn.destroy();
		return returnMap;
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updateTableStatus(
			@RequestParam("number_code") String number_code,
			@RequestParam("status") int status
	) 
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		DBConn conn = DBConn.getInstance();
		String sql = "update bookTable set status='"+status+"' where number_code='"+number_code+"'";
		
		if( conn.insertSQL(sql) ) {
			returnMap.put("returnCode", 0);
			returnMap.put("returnMessage", "table add success!");
		}
		else {
			returnMap.put("returnCode", 1);
			returnMap.put("returnMessage", "Failed,please try again!");
		}
		conn.destroy();
		return returnMap;
	}
	
}
