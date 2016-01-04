package com.hackthon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackthon.domain.User;
import com.hackthon.persistence.UserDataPersistence;



@Service
public class UserService {
	@Autowired
	private UserDataPersistence userDataPersistence;
	
	public User login(String username, String password) throws Exception {
		if(username == null || password == null)
			throw new Exception("Either username or password is empty.");
		User user = userDataPersistence.findUser(username);
		if (user != null ) {
			if(!password.equals(user.getPassword()))
					throw new Exception("User cannot be authenticated.");
			return user;
		} else {
			throw new Exception("User cannot be found.");
		}

	}
	
	public void register(String firstName,String secondName,String userName, String passWord) {

		// 构建查询条件
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", userName);
		List<userdata> userList = userDao.findUser(queryMap);
		if (userList != null && userList.size() != 0) {
			return null;
		} else {
			userdata user = new userdata();
			user.setUsername(userName);
			user.setPassword(passWord);
			user.setFirstName(firstName);
			user.setSecondName(secondName);
			user.setEmail(email);
			user.setIsAvailable(0);
			userDao.insertUser(user);
			userList = userDao.findUser(queryMap);
			if (userList != null && userList.size() > 0)
				return userList.get(0);
			else
				return null;

		}

	}

}
