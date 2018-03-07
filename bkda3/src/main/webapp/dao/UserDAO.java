package main.webapp.dao;

import java.util.List;

import main.webapp.model.User;

public interface UserDAO {
	User findById(long id);
	List<User> getAll();
	boolean save(User user);
	boolean update(User user);
}
