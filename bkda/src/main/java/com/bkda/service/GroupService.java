package com.bkda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkda.dao.UserDAO;
import com.bkda.dto.GroupDTO;
import com.bkda.model.Group;

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
	
	public void inviteMember() {
		
	}
	
	public void requestToJoinGroup() {
		
	}
}
