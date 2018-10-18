package com.bkda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkda.dto.ContentResponse;
import com.bkda.dto.GroupDTO;
import com.bkda.model.Group;
import com.bkda.model.User;
import com.bkda.service.GroupService;
import com.bkda.service.UserService;

@RestController
@RequestMapping(path = "api/v1/groups")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    public ResponseEntity<ContentResponse<Group>> getGroupByUserId(@PathVariable("uid") long uid) {
        Group group = groupService.getGroupByUser(uid);
        ContentResponse<Group> response = new ContentResponse<>();
        response.setContent(group);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@RequestMapping(path = "/request", method = RequestMethod.POST)
    public ResponseEntity<?> requestJoiningGroup(@PathVariable("userId") final long userId) {
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
 
    @RequestMapping(path = "/activate", method = RequestMethod.POST)
    public ResponseEntity<ContentResponse<Group>> activateGroup(@RequestBody GroupDTO groupInfo) {
    	Group group = groupService.createGroup(groupInfo);
        ContentResponse<Group> response = new ContentResponse<>();
        response.setContent(group);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/search")
    public ResponseEntity<?> search(@PathVariable("groupId") final String groupId) {
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
}
