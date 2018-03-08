package com.bkda.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserLoginService {
	UserDetails loadUserByUsername(String username);
}
