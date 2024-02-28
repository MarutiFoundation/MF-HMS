package com.mfp.api.service;

import java.sql.Date; 
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mfp.api.entity.Role;
import com.mfp.api.entity.User;
import com.mfp.api.security.CustomUserDetail;

@Transactional
public interface UserService {
	CustomUserDetail loadUserByUserId(String userId);

	boolean addUser(User user);

	User loginUser(User user);

	String deleteUser(String userName);

	User getUserByUserName(String username);

	List<User> getAllUsers();

	User updateUser(String username , User user);

	Long getUsersTotalCounts();

	Long getUsersTotalCounts(String type);

	Long getUserCountByDateAndType(Date registeredDate, String type);

	List<User> getUserByFirstName(String firstName);

	Role addRole(Role role);

	public Role getRoleById(int roleId);
	
	
	public String generateReport();

	
	
	

}
