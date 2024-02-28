package com.mfp.api.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mfp.api.dao.UserDao;
import com.mfp.api.entity.User;

@Component
public class UserFieldChecker {
	
	@Autowired
	private UserDao dao;
	
	public boolean userAlreadyExists(User user) {
		User isExist = dao.getUserByUserName(user.getUsername());
		if(isExist != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public Map<String, Map<String , String>> duplicateFields(User user){
		
		HashMap<String, String> error = new HashMap<>();
		
		HashMap<String, Map<String , String>> finalMap = new HashMap<String, Map<String,String>>();
 		
		List<User> allUsers = dao.getAllUsers();
		
		for(User sigleUser : allUsers) {
			String emailid = sigleUser.getEmailid();
			String mobileno = sigleUser.getMobileno();
			
			if(user.getEmailid().equals(emailid)) {
				error.put("Email Id Must Be Unique", user.getEmailid());
				finalMap.put("", error);
			}
			
			if(user.getMobileno().equals(mobileno)) {
				error.put("Mobile Number Must Be Unique", user.getMobileno());
				finalMap.put("", error);
			}
		}
		
		
		return finalMap;
		
	}
	

}
