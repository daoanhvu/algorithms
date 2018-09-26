package com.bkda.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkda.model.User;

public interface UserService {
	User findUserById(long id);
	User saveUser(User user);
	List<User> getUsersByName(String name);
	Page<User> search(String username, String firstname, String lastname, Character sex, Pageable paging);
	boolean isUserExist(long id);
}
