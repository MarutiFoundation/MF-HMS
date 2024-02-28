package com.mfp.api.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.mfp.api.entity.Role;

@Component
public class ValidateRole {
	
	public boolean validateRole(Role role) {
		return Pattern.compile("^[a-zA-Z]{4}_[a-zA-Z]+$").matcher(role.getName()).matches();
		// This Regular Expression Checks The Incoming String Must Contains 4 Characters at beginning 
		// After 4 Characters It Must Contain Underscore and after underscore Only Characters allowed
		// Example AAAA_XXXX

	}

}
