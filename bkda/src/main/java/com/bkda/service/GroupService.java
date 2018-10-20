package com.bkda.service;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkda.dao.UserDAO;
import com.bkda.dto.GroupDTO;
import com.bkda.model.Group;
import com.bkda.model.GroupMember;
import com.bkda.model.ToDo;
import com.bkda.model.User;
import com.bkda.model.UserGroup;
import com.bkda.model.UserGroup.UserGroupRole;

@Service
public class GroupService {
	
	@Autowired
	private UserDAO userDAO;

	public Group getGroupByUser(long userid) {
		return userDAO.getGroupByUserId(userid);
	}
	
	public Group createGroup(GroupDTO groupdto) {
		return userDAO.createGroupByUser(groupdto);
	}
	
	public Page<GroupMember> getMembers(long groupId, Pageable paging) {
		return userDAO.getGroupMembers(groupId, paging);
	}
	
	public UserGroup addUserToGroup(long userId, long groupId, int roleIdex) {
		UserGroup.UserGroupRole role = UserGroupRole.values()[roleIdex];
		return userDAO.addUserToGroup(userId, groupId, role);
	}
	
	public UserGroup addUserToGroup(long userId, long groupId, UserGroupRole role) {
		return userDAO.addUserToGroup(userId, groupId, role);
	}
	
	public ToDo inviteMember(long inviterId, long groupId, long userId) {
		
		User inviter = userDAO.findUserById(inviterId);
		User invitee = userDAO.findUserById(userId);
		Group group = userDAO.findGroupById(groupId);
		
		ToDo todo = new ToDo();
		Date now = Date.from(Instant.now(Clock.systemUTC()));
		todo.setActionType(ToDo.ActionType.INVITATION_TO_GROUP);
		todo.setCreateTime(now);
		todo.setLastUpdatedTime(now);
		todo.setSourceUser(inviter);
		todo.setDestination(invitee);
		todo.setGroup(group);
		todo.setStatus(ToDo.ToDoStatus.PENDING);
		
		return userDAO.saveToDo(todo);
	}
	
	public void requestToJoinGroup() {
		
	}
}
