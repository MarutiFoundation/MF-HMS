package com.mfp.api.serviceimpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mfp.api.dao.UserDao;
import com.mfp.api.entity.Role;
import com.mfp.api.entity.User;
import com.mfp.api.exception.ResourceNotFoundException;
import com.mfp.api.exception.SomethingWentWrongException;
import com.mfp.api.security.CustomUserDetail;
import com.mfp.api.service.UserService;
import com.mfp.api.utility.UserFieldChecker;
import com.mfp.api.validation.ValidateRole;
import com.mfp.api.validation.ValidateUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDao dao;
	
	@Autowired
	private ValidateRole validateRole;
	
	@Autowired
	private ValidateUser validateUser;
	
	@Autowired
	private UserFieldChecker checker;
	
	

	@Override
	public boolean addUser(User user) {
			
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedTimestamp = sdf.format(currentTimestamp);
		try {
			java.util.Date utilDate = sdf.parse(formattedTimestamp);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			user.setCreatedDate(sqlDate);	// SETTING DATE IN USER
		} catch (ParseException e) {
			Log.error(e.getMessage());
		}

		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);	//SETTING ENCODED PASSWORD IN USER
		
		 boolean exists = checker.userAlreadyExists(user);
		 
		 if(exists) {
			 throw new SomethingWentWrongException("Something Went Wrong" + "\n" + "User Already Exist, UserName : " + user.getUsername());
		 }else {
			 Map<String, String> isValidate = validateUser.validateUser(user);
				
				if(isValidate.isEmpty()) {
					// method call UserFieldChecker
					
					Map<String, Map<String, String>> duplicateFields = checker.duplicateFields(user);
					if(duplicateFields.isEmpty()) {
						boolean addedUser = dao.addUser(user);
						if(addedUser) {
							return true;
						}else {
							throw new SomethingWentWrongException("User Not Added....");
						}
					}else {
						throw new SomethingWentWrongException("Unique Fileds Required .." + duplicateFields);
					}
					
				}else{
					throw new SomethingWentWrongException(isValidate + "\n" + "Please Enter Valid Details");
				}
		 }
	}

	@Override
	public User loginUser(User user) {

		return dao.loginUser(user);
	}

	@Override
	public CustomUserDetail loadUserByUserId(String userId) {
		return dao.loadUserByUserId(userId);
	}

	@Override
	public User getUserByUserName(String username) {
		return dao.getUserByUserName(username);
	}

	@Override
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public User updateUser(String username , User user) {
		User dbUser = this.dao.getUserByUserName(username);
		if(dbUser == null) {
			throw new ResourceNotFoundException("NO USER FOUND FOR USERNAME : " + user.getUsername());
		}else {
		String password = passwordEncoder.encode(user.getPassword());
		dbUser.setPassword(password);
		dbUser.setAnswer(user.getAnswer());
		dbUser.setCity(user.getCity());
		dbUser.setEmailid(user.getEmailid());
		dbUser.setFirstname(user.getFirstname());
		dbUser.setLastname(user.getLastname());
		dbUser.setMobileno(user.getMobileno());
		dbUser.setPincode(user.getPincode());
		dbUser.setStreet(user.getStreet());
		dbUser.setQuestion(user.getQuestion());
		dbUser.setType(user.getType());
		
		return this.dao.updateUser(dbUser); // User | Null
		}
	}

	@Override
	public Long getUsersTotalCounts() {
		return dao.getUsersTotalCounts();
	}

	@Override
	public Long getUsersTotalCounts(String type) {
		return dao.getUsersTotalCounts(type);
	}

	@Override
	public Long getUserCountByDateAndType(Date registereddate, String type) {
		return dao.getUserCountByDateAndType(registereddate, type);
	}

	@Override
	public List<User> getUserByFirstName(String firstName) {
		return dao.getUserByFirstName(firstName);
	}

	@Override
	public Role addRole(Role role) {
		
		boolean isValid = this.validateRole.validateRole(role);
		
		if(isValid) {
			 Role savedRole = this.dao.addRole(role);
			 if(savedRole == null) {
				 throw new SomethingWentWrongException("Given Role Already Exist..." + "\n" + "Role ID : " + role.getId() + "\n" + "Role Name : " + role.getName());
					
			 }else {
				 return savedRole;
			 }
		}else {
			throw new SomethingWentWrongException("Please Enter Valid Role Name Or Id.." + "\n" + "role id : " + role.getId() + "\n" 
															+ "role name : " + role.getName() + "\n"
															+	"Default Message : Role_Name shold be starts with 'ROLE_ ' also should be starts with charachers only ");
		}
	}

	@Override
	public Role getRoleById(int roleId) {
		return dao.getRoleById(roleId);
	}

	@Override
	public String generateReport() {
		return null;
	}

	@Override
	public String deleteUser(String userName) {
		return this.dao.deleteUser(userName);
		
	}

	
}
