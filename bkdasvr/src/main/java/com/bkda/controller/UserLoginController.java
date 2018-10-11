package com.bkda.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {
	
	@GetMapping("/user/me")
	public Principal user(Principal principal) {
		return principal;
	}
}
