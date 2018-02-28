package com.bkda.service;

import java.util.List;

import com.bkda.entity.User;

public interface UserService {
	User findUserById(long id);
	long saveUser(User user);
	List<User> getUsersByName(String name);
	List<User> allUsers();
	boolean isUserExist(long id);
}
