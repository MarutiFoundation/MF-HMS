package com.mfp.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfp.api.entity.User;
import com.mfp.api.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static Logger LOG = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping(value = "get-user-by-id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		return null;
	}
}