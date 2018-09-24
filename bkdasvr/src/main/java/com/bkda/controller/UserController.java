package com.bkda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkda.model.User;
import com.bkda.service.UserLoginService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> newUser(@RequestBody User user) {
		return null;
	}
}
