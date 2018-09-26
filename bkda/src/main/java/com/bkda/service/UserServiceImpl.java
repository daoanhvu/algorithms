package com.bkda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkda.dao.UserDAO;
import com.bkda.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;

	@Override
	public User findUserById(long id) {
		return userDAO.findUserById(id);
	}

	@Override
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> search(String username, String firstname, String lastname, 
			Character sex, Pageable paging) {
		return userDAO.search(username, firstname, lastname, sex, paging);
	}

	@Override
	public boolean isUserExist(long id) {
		// TODO Auto-generated method stub
		return userDAO.isUserExist(id);
	}

	@Override
	public User saveUser(User user) {
		userDAO.saveUser(user);
		return user;
	}

}
