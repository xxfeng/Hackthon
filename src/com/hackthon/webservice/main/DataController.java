package com.hackthon.webservice.main;
import com.hackthon.base.DBConn;

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

}
