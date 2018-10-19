package com.bkda.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkda.dto.GroupDTO;
import com.bkda.model.Group;
import com.bkda.model.Scope;
import com.bkda.model.User;

public interface UserDAO {
	boolean updateUser(User user);
	User findUserById(long id);
	User checkSignin(String username, String hashedPassword);
	User saveUser(User user);
	Scope saveScope(Scope scope);
	List<User> getUsersByName(String name);
	Page<User> search(String username, Long groupId, String lastname, String firstname, Character sex, Pageable paging);
	boolean isUserExist(long id);
	
	/*=======================================*/
	Group getGroupByUserId(long uid);
	Group createGroupByUser(GroupDTO groupdto);
}
