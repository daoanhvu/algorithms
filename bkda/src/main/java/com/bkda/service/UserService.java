package com.bkda.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkda.dto.CredentialsDTO;
import com.bkda.dto.SigninDTO;
import com.bkda.dto.SignupDTO;
import com.bkda.dto.UserDTO;
import com.bkda.model.Group;
import com.bkda.model.User;

public interface UserService {
	User findUserById(long id);
	User updateUser(User user);
	User signup(SignupDTO user);
	CredentialsDTO signin(SigninDTO signinDTO);
	List<User> getUsersByName(String name);
	Page<User> search(String username, String firstname, String lastname, Character sex, Pageable paging);
	boolean isUserExist(long id);
}
