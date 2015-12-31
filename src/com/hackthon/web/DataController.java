package com.hackthon.web;
import com.hackthon.base.DBConn;
import com.hackthon.dao.TvInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *@author 作者 Email:
 *@version 创建时间：2014-2-18下午9:12:44
 *@类说明
 */
@Controller
@RequestMapping("/text")
public class DataController {
	@RequestMapping("/reg")
	@ResponseBody
	public Map<String, Object> login(@RequestParam("param") String param) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		/*
		//调用处理程序
		String sSQL="select * from TvInfo";
		DBConn helper=new DBConn(); 
		
		ResultSet rs=helper.GetResultSet(sSQL, null); 
        try 
        { 
            if(rs.next()) 
                System.out.println("id: " + rs.getString(1) + " brand_name: " + rs.getString(2)); 
                ////索引从1开始 
        }catch(Exception ex) 
        { 
            System.out.println(ex.getMessage()); 
        }finally 
        { 
            try 
            { 
                rs.close(); 
            }catch(Exception ex) 
            { 
                System.out.println(ex.getMessage()); 
            } 
        }
		*/
	
		
		int returnCode = 1;
		
		//返回数据给页面
		returnMap.put("returnCode", returnCode);
		returnMap.put("got", param);
		returnMap.put("result", "Hello, Hackthon, I am backend ^ ^ ");
		return returnMap;
	}
	
	
	@RequestMapping("/getHomeTvs")
	@ResponseBody
	public Map<String, Object>  getHomeTvs( ) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<TvInfo> returnList = new ArrayList<TvInfo>();
		//调用处理程序
		String sSQL="select top 6 name,imageURL,pdates from TvInfo order by pdates";
		DBConn helper=new DBConn(); 
		
		ResultSet rs=helper.GetResultSet(sSQL, null); 
        try 
        { 
            while(rs.next()) {
            	TvInfo tvinfo = new TvInfo();
            	tvinfo.setNAME(rs.getString(1));
            	tvinfo.setPDATES(rs.getString(2));
            	tvinfo.setPDATES(rs.getString(3));
            	returnList.add(tvinfo);
            }
        }catch(Exception ex) 
        { 
            System.out.println(ex.getMessage()); 
        }finally 
        { 
            try 
            { 
                rs.close(); 
            }catch(Exception ex) 
            { 
                System.out.println(ex.getMessage()); 
            } 
        }

		returnMap.put("returnCode", Integer.valueOf(1));
		returnMap.put("data",  returnList);
		return returnMap;
	}

}
