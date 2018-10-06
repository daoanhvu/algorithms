package com.bkda.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkda.model.User;

public interface UserDAO {
	boolean updateUser(User user);
	User findUserById(long id);
	User checkSignin(String username, String hashedPassword);
	long saveUser(User user);
	List<User> getUsersByName(String name);
	Page<User> search(String username, String lastname, String firstname, Character sex, Pageable paging);
	boolean isUserExist(long id);
}
