package com.mfp.api.controller;

import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfp.api.entity.User;
import com.mfp.api.exception.ResourceNotFoundException;
import com.mfp.api.exception.SomethingWentWrongException;
import com.mfp.api.model.JwtResponse;
import com.mfp.api.model.ResetPasswordDetail;
import com.mfp.api.security.CustomUserDetailService;
import com.mfp.api.service.EmailPasswordService;
import com.mfp.api.service.UserService;
import com.mfp.api.utility.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private static Logger LOG = LogManager.getLogger(AuthController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	EmailPasswordService emailPasswordService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login-user")
	public ResponseEntity<?> loginAdmin(@RequestBody User user,HttpServletResponse response) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtUtil.generateToken(authentication); 
        response.addHeader("token", token);
       return ResponseEntity.ok(new JwtResponse(token));
    
    }



	@PostMapping(value = "/reset-password-by-otp")
	public ResponseEntity<String> resetPasswordByOtp(@RequestBody ResetPasswordDetail detail) {
		
		User userName = this.userService.getUserByUserName(detail.getUserId());
		if(userName != null){
			String passwordByOtp = this.emailPasswordService.resetPasswordByOtp(detail);
			if(passwordByOtp.equals("UPDATED")) {
				return new ResponseEntity<>("PASSWORD UPDATED SUCCESSFULLY....", HttpStatus.OK);
			}else if(passwordByOtp.equals("NOT UPDATED")) {
				throw new SomethingWentWrongException("UNABLE TO RESET PASSWORD...");
			}else if(passwordByOtp.equals("PasswordNotMatched")) {
				throw new SomethingWentWrongException("NEW PASSWORD AND CONFIRM PASSWORD DOEST NOT MATCHED...");
			}else if(passwordByOtp.equals("OTPERROR")) {
				throw new SomethingWentWrongException("WRONG OTP...");
			}else {
				throw new SomethingWentWrongException("FETAL ERROR...");
			}
		}else{
			throw new ResourceNotFoundException("USER NOT FOUND.. USER NAME : " + detail.getUserId());
		}

	}
	
	@PostMapping(value = "/reset-password-by-qa")
	public ResponseEntity<String> resetPasswordByQA(@RequestBody ResetPasswordDetail detail) {
		User userName = this.userService.getUserByUserName(detail.getUserId());
		if(userName != null){
			String msg = this.emailPasswordService.resetPasswordByQA(detail);
			
			if(msg.equals("OK")) {
				return new ResponseEntity<String>("PASSWORD UPDATED SUCCESSFULY......." , HttpStatus.OK);
			}else if(msg.equals("WROENG QUESTION...")) {
				throw new SomethingWentWrongException("WROENG QUESTION...");
			}else if(msg.equals("WROENG ANSWER...")) {
				throw new SomethingWentWrongException("WROENG ANSWER...");
			}else if(msg.equals("NEW PASSWORD AND CONFIRM PASSWORD NOT MATCHED...")) {
				throw new SomethingWentWrongException("NEW PASSWORD AND CONFIRM PASSWORD NOT MATCHED...");
			}else {
				throw new SomethingWentWrongException("USER NOT UPDATED....");
			}
		}else{
				throw new ResourceNotFoundException("USER NOT FOUND.. USER NAME : " + detail.getUserId());
			}
	}

}
