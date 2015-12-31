package com.hackthon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackthon.dao.UserDao;
import com.hackthon.domain.userdata;

/**
 * @author 作者 Email:xiangping165@sina.com
 * @version 创建时间：2013-12-15下午10:33:35
 * @类说明 用户管理
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 用户登录
	 * 
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @return 当前登录用户
	 */
	public userdata login(String userName, String passWord) {

		// 构建查询条件
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", userName);
		queryMap.put("password", passWord);

		List<userdata> userList = userDao.findUser(queryMap);
		if (userList != null && userList.size() != 0) {

			return userList.get(0);

		} else {
			return null;
		}

	}

	/**
	 * 用户注册
	 * 
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @return 返回当前注册用户
	 */
	public userdata register(String firstName,String secondName,String email,String userName, String passWord) {

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
