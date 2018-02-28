package com.bkda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bkda.entity.CustomErrorType;
import com.bkda.entity.User;
import com.bkda.service.UserService;

@RestController
public class HomeController {
	
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    @Autowired
    private UserService userService; //Service which will do all data retrieval/manipulation work
    
    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Login login) {
        logger.info("Creating User : {}" + login);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity("", headers, HttpStatus.OK);
    }
    
}
