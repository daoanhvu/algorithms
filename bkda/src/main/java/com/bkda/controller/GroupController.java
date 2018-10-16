package com.bkda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/groups")
public class GroupController {
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createGroup(ModelMap modal) {
        modal.addAttribute("title","AngularJS CRUD Example");
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
	
	@RequestMapping(path = "/users/{userId}")
    public ResponseEntity<?> getGroupByUserId(@PathVariable("userId") final long userId) {
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
 
    @RequestMapping(path = "/{groupId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("groupId") final String groupId) {
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @RequestMapping(path = "/search")
    public ResponseEntity<?> search(@PathVariable("groupId") final String groupId) {
    	return new ResponseEntity<>("", HttpStatus.OK);
    }
}
