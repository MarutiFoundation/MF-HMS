package com.mfp.api.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.mfp.api.entity.User;

@Component
public class ValidateUser {
	
	boolean status;
	
	public Map<String, String> validateUser(User user) {
				
		HashMap<String, String> errorMessage = new HashMap<>();
		
		if(user.getUsername() != null) {
			status = Pattern.compile("^.{6,}$").matcher(user.getUsername()).matches();// letters more thn 5	
			if(status == false) {
				errorMessage.put("User Name Must Be Greater Than 5 Letters", user.getUsername());
			}
		}else {
			errorMessage.put("User Name Should Not Be Null..",user.getUsername());
		}
		
		
		if(user.getFirstname() != null) {		
			status = Pattern.compile("^[a-zA-Z]{4,}$"/*"^[a-zA-Z]+$"*/).matcher(user.getFirstname()).matches(); // only character more thn 3	
			if(status == false) {
				errorMessage.put("First Name Should Contains more than 3 Character", user.getFirstname());
			}
		}else {
			errorMessage.put("First Name Should not be null", user.getFirstname());
		}
		
		if(user.getLastname() != null) {
			status = Pattern.compile("^[a-zA-Z]{4,}$"/*"^[a-zA-Z]+$"*/).matcher(user.getLastname()).matches(); // only character more thn 3	
			if(status == false) {
				errorMessage.put("Last Name Should Contains more than 3 Character", user.getLastname());
				
			}
		}else {
			errorMessage.put("Last Name Should not be null", user.getLastname());
		}
		
		if(user.getEmailid() != null) {
			status = Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.[A-Za-z]{2,}$").matcher(user.getEmailid()).matches();
			if(status == false) {
				
				errorMessage.put("Please Enter Valid Email Id {abc@gmail.com}", user.getEmailid());
			}
		}else {
			errorMessage.put("Email Id Should Not Be Null", user.getEmailid());
		}
		
		if(user.getPassword() != null) {
			status = Pattern.compile("^.{6,}$").matcher(user.getPassword()).matches();
			if(status == false) {
				errorMessage.put("Password Shold Be More Than 5 letters" , user.getPassword());
			}
		}else {
			errorMessage.put("Password Shold Not Be Null", user.getPassword());
		}
		
		if(user.getMobileno() != null) {
			status = Pattern.compile("^[0-9]{10}$").matcher(user.getMobileno()).matches();
			if(status == false) {
				errorMessage.put("Mobile Number Must Contains 10 Digits Only", user.getMobileno());
			}
		}else {
			errorMessage.put("Mobile Number Shold Not Be Null", user.getMobileno());
		}
		
		if(user.getStreet() != null) {
			status = Pattern.compile(".{4,}").matcher(user.getStreet()).matches(); // more than 4 letters includes digits and characters
			if(status == false) {
				errorMessage.put("Street Must Contains More Than 3 Letters", user.getStreet());
			}
		}else {
			errorMessage.put("Street Should Not Be Null", user.getStreet());
		}
		
		if(user.getCity() != null) {
			status = Pattern.compile("\\b\\w{3,}\\b").matcher(user.getCity()).matches(); // more than 4 characters
			if(status == false) {
				errorMessage.put("City Name Must Contains More Than 3 Charachters Only", user.getCity());
			}
		}else {
			errorMessage.put("City Name Not Be Null", user.getCity());
		}
		
		if(user.getPincode() != null) {
			status = Pattern.compile("^[0-9]{6}$").matcher(user.getPincode()).matches(); // must be 6 digits
			if(status == false) {
				errorMessage.put("Pincode Must Contains 6 Digits", user.getPincode());
			}
		}else {
			errorMessage.put("Pincode Should Not Be Null", user.getPincode());
		}
		
		if(user.getType() != null) {
			status = Pattern.compile("^[A-Za-z]{4,}$").matcher(user.getType()).matches(); // must be 6 digits
			if(status == false) {
				errorMessage.put("Type is too Short ...., Please Enter Type Name More Than 5 Charachters Only", user.getType());
			}
		}else {
			errorMessage.put("Type Should Not Be Null", user.getType());
		}
		
		if(user.getAnswer().isEmpty() /*&& user.getAnswer().isBlank()*/) {
			errorMessage.put("Answer Should Not Be Null", user.getAnswer());
		}
		
		return errorMessage;	
	}

}
