package com.mfp.api.utility;

public class ValidateInputData {
	
	public static boolean validateUserName(String userName) {
		if(userName != null && !userName.trim().isEmpty())
			return true;
		else
			return false;
		
	}
	

}
