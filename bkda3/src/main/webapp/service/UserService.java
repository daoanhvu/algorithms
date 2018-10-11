package main.webapp.service;

import java.util.List;

import main.webapp.model.User;

public interface UserService {
	User findById(long id);
	boolean saveOrUpdate(User user);
	
	List<User> getAll();
}
