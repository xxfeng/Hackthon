package com.hackthon.webservice.User;

import java.util.HashMap;
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

import com.hackthon.domain.User;
import com.hackthon.representation.UserDataRepresentation;
import com.hackthon.service.UserService;

@Controller
public class UserManagerController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> login(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int returnCode = 0;
		try
		{
			System.out.println(username+password);
			User user = userService.login(username, password);
			returnCode = 1;
			session.setAttribute("loginedUser", username);
			returnMap.put("returnCode", returnCode);
			returnMap.put("user", new UserDataRepresentation(user.getUsername(), user.getPassword(), 
					user.getFirstName(), user.getLastName()));
		}catch(Exception err)
        {
			returnCode = 0;
			returnMap.put("returnCode", returnCode);
		}
		return returnMap;
	}
	
	@RequestMapping(value = "/register",  method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, Object> register(@RequestBody UserDataRepresentation user)
	{ 
		System.out.println(user.getFirstname());
		
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Map<String, Object> returnMap = new HashMap<String, Object>();
	 	int returnCode = 0;
        try
        {
        	User _user = userService.register(user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword());
			returnCode = 1;
			session.setAttribute("loginedUser", user.getUsername());
			returnMap.put("returnCode", returnCode);
			returnMap.put("user", new UserDataRepresentation(_user.getUsername(), _user.getPassword(), 
					_user.getFirstName(), _user.getLastName()));
        }catch(Exception err)
        {
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
