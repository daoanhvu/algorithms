package com.bkda.dao;

import java.util.List;

import com.bkda.entity.User;

public interface UserDAO {
	boolean updateUser(User user);
	User findUserById(int id);
	int addNewUser(User user);
	List<User> getUsersByName(String name);
	List<User> allUsers();
	boolean isUserExist(int id);
}
