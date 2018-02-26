package com.bkda.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkda.dao.UserDAO;
import com.bkda.entity.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User findUserById(int id) {
		return userDAO.findUserById(id);
	}

	@Override
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> allUsers() {
		// TODO Auto-generated method stub
		return userDAO.allUsers();
	}

	@Override
	public boolean isUserExist(int id) {
		// TODO Auto-generated method stub
		return userDAO.isUserExist(id);
	}

	@Override
	@Transactional
	public int saveUser(User user) {
		userDAO.addNewUser(user);
		return user.getId();
	}

}
