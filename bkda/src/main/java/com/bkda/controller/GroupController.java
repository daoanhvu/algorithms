package com.bkda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkda.dto.GroupDTO;
import com.bkda.dto.response.ContentResponse;
import com.bkda.model.Group;
import com.bkda.model.GroupMember;
import com.bkda.model.User;
import com.bkda.service.GroupService;

@RestController
@RequestMapping(path = "api/v1/groups")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/user/{uid}", 
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentResponse<Group>> getGroupByUserId(@PathVariable("uid") long uid) {
        Group group = groupService.getGroupByUser(uid);
        ContentResponse<Group> response = new ContentResponse<>();
        response.setContent(group);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@RequestMapping(path = "/list/user/{uid}",
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentResponse<List<Group>>> getUserGroups(@PathVariable("uid") final long uid) {
		List<Group> groups = groupService.getUserGroups(uid);
		ContentResponse<List<Group>> response = new ContentResponse<>(groups);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@RequestMapping(path = "/request", method = RequestMethod.POST)
    public ResponseEntity<?> requestJoiningGroup(@PathVariable("userId") final long userId) {
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
 
    @RequestMapping(path = "/activate",
    		method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentResponse<Group>> activateGroup(@RequestBody GroupDTO groupInfo) {
    	Group group = groupService.createGroup(groupInfo);
        ContentResponse<Group> response = new ContentResponse<>();
        response.setContent(group);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/members/{groupId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentResponse<Page<GroupMember>>> getMembers(
            @PathVariable("groupId") long groupId,
            Pageable paging, @RequestHeader("Authorization") String authorization) {
    	
    	Page<GroupMember> result = groupService.getMembers(groupId, paging);
    	ContentResponse<Page<GroupMember>> response = new ContentResponse<>();
        response.setContent(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
