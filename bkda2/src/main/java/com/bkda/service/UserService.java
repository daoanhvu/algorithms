package com.bkda.service;

import java.util.List;

import com.bkda.entity.User;

public interface UserService {
	User findUserById(int id);
	int saveUser(User user);
	List<User> getUsersByName(String name);
	List<User> allUsers();
	boolean isUserExist(int id);
}
