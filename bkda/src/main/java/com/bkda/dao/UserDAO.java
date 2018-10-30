package com.bkda.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bkda.dto.GroupDTO;
import com.bkda.model.Group;
import com.bkda.model.GroupMember;
import com.bkda.model.Scope;
import com.bkda.model.ToDo;
import com.bkda.model.User;
import com.bkda.model.UserGroup;

public interface UserDAO {
	User updateUser(User user);
	User findUserById(long id);
	User checkSignin(String username, String hashedPassword);
	User saveUser(User user);
	Scope saveScope(Scope scope);
	List<User> getUsersByName(String name);
	Page<User> search(String username, Long groupId, String lastname, String firstname, Character sex, Pageable paging);
	boolean isUserExist(long id);
	
	/*=======================================*/
	Group findGroupById(long id);
	Group getGroupByUserId(long uid);
	Group createGroupByUser(GroupDTO groupdto);
	UserGroup addUserToGroup(long userId, long groupId, UserGroup.UserGroupRole role);
	// TODO: Check if this method is redundant then remove it
	Page<User> getGroupUsers(long groupId, Pageable paging);
	Page<GroupMember> getGroupMembers(long groupId, Pageable paging);
	List<Group> getUserGroups(long uid);
	/*====== TO DO ==========*/
	ToDo saveToDo(ToDo todo);
	List<ToDo> getToDoListOf(long userId);
}
