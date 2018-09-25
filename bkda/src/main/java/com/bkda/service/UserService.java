package com.bkda.service;

import java.util.List;

import com.bkda.model.User;

public interface UserService {
	User findUserById(long id);
	User saveUser(User user);
	List<User> getUsersByName(String name);
	List<User> search(String username, String lastname, String firstname);
	boolean isUserExist(long id);
}
