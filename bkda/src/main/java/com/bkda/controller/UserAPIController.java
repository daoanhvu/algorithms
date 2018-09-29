package com.bkda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkda.dto.ContentResponse;
import com.bkda.dto.CredentialsDTO;
import com.bkda.dto.SigninDTO;
import com.bkda.dto.UserDTO;
import com.bkda.model.User;
import com.bkda.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users")
public class UserAPIController {
 
    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work
 
    // -------------------Retrieve All Users---------------------------------------------
 
    @ApiOperation("Search for users by their name, sex, email")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "Authorization", dataType = "string", 
    			paramType = "header", value = "Authorization token", required = true),
    	@ApiImplicitParam(name = "api-key", dataType = "string", 
			paramType = "header", value = "api key", required = true)
    })
    @RequestMapping(value = "/search", method = RequestMethod.POST, 
    	produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentResponse<Page<UserDTO>>> search(@RequestBody UserDTO criteria, Pageable paging) {
        Page<User> page = userService.search(criteria.getEmail(), 
        		criteria.getFirstName(), criteria.getLastName(), criteria.getSex(), paging);
        List<UserDTO> userDtos = page.getContent().stream().map(UserDTO::new).collect(Collectors.toList());
        Page<UserDTO> p = new PageImpl<>(userDtos, paging, page.getTotalElements());
        ContentResponse<Page<UserDTO>> result = new ContentResponse<>();
        result.setContent(p);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single User------------------------------------------
 
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ContentResponse<UserDTO>> getUser(@PathVariable("id") int id) {
        User user = userService.findUserById(id);
        UserDTO userDto = new UserDTO(user);
        ContentResponse<UserDTO> response = new ContentResponse<>();
        response.setContent(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 
    // -------------------Create a User-------------------------------------------
 
    @ApiOperation("Create a new user")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "Authorization", dataType = "string", 
    			paramType = "header", value = "Authorization token", required = true),
    	@ApiImplicitParam(name = "api-key", dataType = "string", 
			paramType = "header", value = "api key", required = true)
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContentResponse<UserDTO>> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        UserDTO userDto = new UserDTO(savedUser);
        ContentResponse<UserDTO> response = new ContentResponse<>();
        response.setContent(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
 
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContentResponse<UserDTO>> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        UserDTO userDto = new UserDTO(savedUser);
        ContentResponse<UserDTO> response = new ContentResponse<>();
        response.setContent(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 
    // ------------------- User Signin -----------------------------------------
 
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<CredentialsDTO> signin(@RequestBody SigninDTO signinDTO) {
        CredentialsDTO result = userService.signin(signinDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
 
    // ------------------- Delete All Users-----------------------------
 
//    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//    public ResponseEntity<User> deleteAllUsers() {
//        logger.info("Deleting All Users");
//        userService.deleteAllUsers();
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
}
