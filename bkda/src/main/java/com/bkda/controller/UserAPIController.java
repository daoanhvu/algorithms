package com.bkda.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bkda.dto.ContentResponse;
import com.bkda.dto.UserDTO;
import com.bkda.model.User;
import com.bkda.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users")
public class UserAPIController {
	public static final Logger logger = LoggerFactory.getLogger(UserAPIController.class);
	 
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
    @RequestMapping(value = "/search", method = RequestMethod.GET, 
    	produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User>> search() {
        List<User> users = userService.search(null, null, null);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
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
 
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContentResponse<UserDTO>> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        User savedUser = userService.saveUser(user);
        UserDTO userDto = new UserDTO(savedUser);
        ContentResponse<UserDTO> response = new ContentResponse<>();
        response.setContent(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
 
    // ------------------- Update a User ------------------------------------------------
 
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContentResponse<UserDTO>> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        UserDTO userDto = new UserDTO(savedUser);
        ContentResponse<UserDTO> response = new ContentResponse<>();
        response.setContent(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
//        logger.info("Fetching & Deleting User with id {}", id);
// 
//        User user = userService.findById(id);
//        if (user == null) {
//            logger.error("Unable to delete. User with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
//        }
//        userService.deleteUserById(id);
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
 
    // ------------------- Delete All Users-----------------------------
 
//    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//    public ResponseEntity<User> deleteAllUsers() {
//        logger.info("Deleting All Users");
//        userService.deleteAllUsers();
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
}
