package com.hackthon.web;
import com.hackthon.base.DBConn;
import com.hackthon.dao.TvInfo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/text")
public class DataController {
	@RequestMapping("/reg")
	@ResponseBody
	public Map<String, Object> login(@RequestParam("param") String param) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int returnCode = 1;
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
		String sql="select top 6 name,imageURL,pdates from TvInfo order by pdates";
		DBConn conn = DBConn.getInstance();
		ResultSet rs= conn.selectSQL(sql);
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
