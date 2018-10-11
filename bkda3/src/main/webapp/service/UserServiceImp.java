package main.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.webapp.dao.UserDAO;
import main.webapp.model.User;

@Service("userService")
public class UserServiceImp implements UserService {
	UserDAO userDAO;
	
	@Autowired
	public void setUserDAO(UserDAO udao) {
		this.userDAO = udao;
	}
	
	@Override
	public User findById(long id) {
		return userDAO.findById(id);
	}
	
	public boolean saveOrUpdate(User user) {
		if( user.getId() > 0 ) {
			return userDAO.update(user);
		}
		
		return userDAO.save(user);
	}
	
	public List<User> getAll() {
		return userDAO.getAll();
	}
}
