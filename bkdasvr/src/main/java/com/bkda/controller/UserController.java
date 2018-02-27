package com.bkda.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkda.model.User;
import com.bkda.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/new", method = RequestMethod.POST)
	public ResponseEntity<?> newUser(@RequestBody User user) {
//		long id = userService.addUser(user);
		long id = 100L;
		String str = "Failed to add new user. Please check data";
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		JSONObject json = new JSONObject();
		
		if(id > 0) {
			str = "{\"status\":\"OK\"}";
			headers.add("userid", String.valueOf(id));
			status = HttpStatus.OK;
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ResponseEntity<JSONObject> re = new ResponseEntity(str, headers, status);
		
		System.out.println(re.toString());
		
		return re;
	}
}
