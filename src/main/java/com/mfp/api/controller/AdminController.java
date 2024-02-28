package com.mfp.api.controller;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfp.api.entity.Role;
import com.mfp.api.entity.User;
import com.mfp.api.exception.InvalidInputException;
import com.mfp.api.exception.ResourceNotFoundException;
import com.mfp.api.exception.SomethingWentWrongException;
import com.mfp.api.service.UserService;
import com.mfp.api.utility.ValidateInputData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;




@RestController
@RequestMapping(value = "/admin")
@Api(tags = "Admin", description = "Endpoints for managing APIS Controlled By ADMIN")
public class AdminController {
	// Last Modified 08-01-2024 
	private static Logger LOG = LogManager.getLogger(AdminController.class);

	@Autowired
	UserService userService;

	@PostMapping("/add-user")
	public ResponseEntity<Boolean> registerUser(@RequestBody User user) {
		boolean isAdded = userService.addUser(user);
		if (isAdded) {
			return new ResponseEntity<>(isAdded, HttpStatus.OK);
		} else {
			throw new SomethingWentWrongException("User Not Saved...");
		}
	}

	@ApiOperation("Delete Specific User Record By UserName")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully Deleted The User"),
			@ApiResponse(code = 400, message = "Input Data is empty"),
			@ApiResponse(code = 404, message = "User Not Found")

	})
	@DeleteMapping(value = "/delete-user/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable String userName) {
		if (ValidateInputData.validateUserName(userName)) {
			String message = this.userService.deleteUser(userName);
			if (message.equals("success")) {
				return ResponseEntity.ok("User with userName " + userName + " deleted successfully.");
			} else {
				throw new ResourceNotFoundException("USER NOT FOUND...." + "|" + "USERNAME: " + userName);
			}
		} else {
			throw new InvalidInputException("User Name Should Not Be Empty : " + userName);

		}
	}

	@PutMapping("/update-user/{username}")
	public ResponseEntity<User> updateUser(@PathVariable String username ,@RequestBody User user) {
		User updateUser = userService.updateUser(username, user);
		if(updateUser != null) {
			return new ResponseEntity<>(updateUser, HttpStatus.FOUND);
		}else {
			throw new SomethingWentWrongException("UNABLE TO UPDATE USER...");
		}
	}

	@GetMapping(value = "get-all-user", produces = "application/json")
	public ResponseEntity<List<User>> getAllAdmin() {
		List<User> list = userService.getAllUsers();
		if (list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("user not found");
		}
	}
	
	

	@ApiOperation("Get a specific entity by ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully Saved The Role"),
			@ApiResponse(code = 409, message = "Role Already Exist In DataBase"),
			@ApiResponse(code = 404, message = "Invadid Data")

	})
	@PostMapping(value = "/add-role")
	public ResponseEntity<Object> addRole(@RequestBody Role role) {
		Role addedRole = userService.addRole(role);
		if (addedRole != null) {
			return new ResponseEntity<>(addedRole, HttpStatus.OK);
		} else {
			throw new SomethingWentWrongException("Role Not Saved...");
		}
	}

	@GetMapping(value = "/get-role-by-id/{roleId}")
	public ResponseEntity<Role> getRoleById(@PathVariable int roleId) {
		Role role = userService.getRoleById(roleId);
		if (role != null) {
			return new ResponseEntity<>(role, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("RESOURCE NOT FOUND FOR ID : " + roleId);
		}
	}
	
	

	@GetMapping(value = "/get-total-count-of user")
	public ResponseEntity<Long> getUsersTotalCounts() {
		Long count = userService.getUsersTotalCounts();
			return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping(value = "/get-total-count-of-user-by-type/{type}")
	public ResponseEntity<Long> getUsersTotalCountsByType(@PathVariable String type) {
		Long usersTotalCounts = userService.getUsersTotalCounts(type);
		
		if(usersTotalCounts > 0) {           
            return ResponseEntity.ok(usersTotalCounts);
        }else {
			throw new ResourceNotFoundException("user not exist for " + "type" + type);
        }
    }
	

	@GetMapping(value = "/get-total-count-of-user-by-date-and-type/{date}/{type}")
	public ResponseEntity<Long> getUserCountByDateAndType(@PathVariable Date date, @PathVariable String type) {

		Long countByDateAndType = userService.getUserCountByDateAndType(date, type);

		if (countByDateAndType > 0) {
			return new ResponseEntity<>(countByDateAndType, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("user not exist for " + "type " + type + "date" + date);
		}

	}

	@GetMapping(value = "/get-user-by-firstname/{firstName}", produces = "application/json")
	public ResponseEntity<List<User>> getUserByFirstName(@PathVariable String firstName) {
		List<User> list = userService.getUserByFirstName(firstName);
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User Not Exists For Name : " + firstName);
		}
	}
	
	@GetMapping(value = "c{username}", produces = "application/json")
	public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
		User user = userService.getUserByUserName(username);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		} else {
			throw new ResourceNotFoundException("USER NOT EXIT WITH USER NAME : " + username);
		}
	}
		
		

	@GetMapping(value = "/user/report")
	public ResponseEntity<String> generateReport() {
		return null;
	}

}
