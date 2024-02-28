package com.mfp.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfp.api.exception.SomethingWentWrongException;
import com.mfp.api.model.EmailDetails;
import com.mfp.api.service.EmailPasswordService;


@RestController
@RequestMapping(value = "/email")
public class EmailController {

	@Autowired
	private EmailPasswordService emailPasswordService;


	@PostMapping(path = "/sendMail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> sendMail(@RequestBody EmailDetails details) {
		boolean sendMail = this.emailPasswordService.sendMail(details);
		if(sendMail)
			return new ResponseEntity<>(sendMail, HttpStatus.OK);
		else
			throw new SomethingWentWrongException("Invalid EmailID....");
	}
	
	@GetMapping(value = "/send-otp/{userId}")
	public ResponseEntity<String> sendOtp(@PathVariable String userId){
		String sendOtp = this.emailPasswordService.sendOtp(userId);
		if(sendOtp.equalsIgnoreCase("OTP Generated Successfully...")) {
			return new ResponseEntity<String>("OTP Generated Successfully...", HttpStatus.OK);
		}else {
			throw new SomethingWentWrongException("UNABLE TO GENERATE OTP...");
		}
	}
	
	// OTP API ----- EMAIL API ----> EMAIL(OTP)
	

}
