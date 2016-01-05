package com.hackthon.service;

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
	
	public User register(String firstname, String lastname, String username, String password) throws Exception{
		User user = userDataPersistence.findUser(username);
		if (user != null){
			throw new Exception ("User exists.");
		}
        user = new User ();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setUsername(username);
        user.setPassword(password);
	    if(userDataPersistence.insertUser(user))
	    	return user;
	    else
	    	throw new Exception("Cannot create user.");
	}

}
