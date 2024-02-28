package com.mfp.api.dao;

import java.sql.Date; 
import java.util.List;
import java.util.Optional;

import com.mfp.api.entity.Otp;
import com.mfp.api.entity.Role;
import com.mfp.api.entity.User;
import com.mfp.api.security.CustomUserDetail;

public interface UserDao {
	public CustomUserDetail loadUserByUserId(String userId);

	boolean addUser(User user);

	User loginUser(User user);

	String deleteUser(String userName);

	User getUserByUserName(String username);

	List<User> getAllUsers();

	User updateUser(User user);

	Long getUsersTotalCounts();

	Long getUsersTotalCounts(String type);

	Long getUserCountByDateAndType(Date registeredDate, String type);

	List<User> getUserByFirstName(String firstName);

	Role addRole(Role role);

	public Role getRoleById(int roleId);

	public boolean saveOtp(Otp otp);

	public Otp getOtpByUser(String userId);
	
	public Optional<User> findByUserName(String userName);

	public void delete(User user);
	
	
	

	
}
