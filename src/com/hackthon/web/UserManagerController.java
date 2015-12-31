package com.hackthon.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hackthon.domain.userdata;
import com.hackthon.service.UserService;

/**
 *@author 作者 Email:xiangping165@sina.com
 *@version 创建时间：2013-12-15下午10:51:48
 *@类说明 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserManagerController {
	
	@Autowired
	private UserService userService;
	
	
	
	/**
	 * 用户登录请求
	 * @param username 用户名
	 * @param password 密码
	 * @return 
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		userdata user=userService.login(username, password);
		int returnCode = 0;
		if (user != null) {
			returnCode = 1;
			session.setAttribute("loginedUser", username);
			returnMap.put("returnCode", returnCode);
			returnMap.put("user", user);
		} else {
			returnCode = 0;
			returnMap.put("returnCode", returnCode);

		}
		return returnMap;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Map<String, Object> register(@RequestParam("firstName")String firstName,@RequestParam("secondName")String secondName,
			@RequestParam("email")String email,@RequestParam("username") String username,
			@RequestParam("password") String password)
			{ 
		    HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		    Map<String, Object> returnMap = new HashMap<String, Object>();
		    userdata user=userService.register(firstName, secondName, email, username, password);
		 	int returnCode = 0;
			if (user != null) {
				
					returnCode = 1;
					session.setAttribute("loginedUser", username);
					returnMap.put("returnCode", returnCode);
					returnMap.put("user", user);
				
				
			} else {
				returnCode = 0;
				returnMap.put("returnCode", returnCode);

			}
			return returnMap;
		
			}
	
	@RequestMapping("/checkout")
	@ResponseBody
	public Map<String, Object> checkOut()
	{
		 HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		 session.removeAttribute("loginedUser");
		 int returnCode = 0;
		 if(session.getAttribute("loginedUser")==null)
		 {
			 returnCode=1;
			 returnMap.put("returnCode", returnCode);
		 }
		 else {
			returnCode=0;
			returnMap.put("returnCode", returnCode);
		}
		
		return returnMap;
	}

}
