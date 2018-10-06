package com.bkda.service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkda.common.Constants;
import com.bkda.dao.UserDAO;
import com.bkda.dto.CredentialsDTO;
import com.bkda.dto.ErrorContent;
import com.bkda.dto.SigninDTO;
import com.bkda.dto.SignupDTO;
import com.bkda.dto.UserDTO;
import com.bkda.exception.BKDAServiceException;
import com.bkda.exception.MultiErrorsException;
import com.bkda.model.User;
import com.google.common.hash.Hashing;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static final String GRANT_TYPE_PASSWORD = "password";
	private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
	private static final String GRANT_TYPE_IMPLICIT = "implicit";
	private static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
	private static final String GRANT_TYPE_DEVICE_CODE = "device_code";
	private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
	private static final String GRANT_TYPE_RESOURCE_OWNER = "resource_owner";
	
	private final String[] grantTypes = {
			GRANT_TYPE_PASSWORD, 
			GRANT_TYPE_AUTHORIZATION_CODE,
			GRANT_TYPE_IMPLICIT,
			GRANT_TYPE_CLIENT_CREDENTIALS,
			GRANT_TYPE_DEVICE_CODE,
			GRANT_TYPE_REFRESH_TOKEN,
			GRANT_TYPE_RESOURCE_OWNER
			};
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public User findUserById(long id) {
		return userDAO.findUserById(id);
	}

	@Override
	public List<User> getUsersByName(String name) {
		return null;
	}

	@Override
	public Page<User> search(String username, String firstname, String lastname, 
			Character sex, Pageable paging) {
		return userDAO.search(username, firstname, lastname, sex, paging);
	}

	@Override
	public boolean isUserExist(long id) {
		return userDAO.isUserExist(id);
	}

	@Override
	public User saveUser(SignupDTO signupDto) {
		List<ErrorContent> errors = validateSignup(signupDto);
		if( errors.size() > 0 ) {
			throw new MultiErrorsException(errors, "Login fails");
		}
		User user = new User();
		user.setUsername(signupDto.getUserName());
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastName());
		user.setSex(signupDto.getSex());
		user.setStartDate( Date.from(Instant.now(Clock.systemUTC())) );
		userDAO.saveUser(user);
		return user;
	}

	@Override
	public CredentialsDTO signin(SigninDTO signinDTO) throws BKDAServiceException {
		
		List<ErrorContent> errors = validateLogin(signinDTO);
		if( errors.size() > 0 ) {
			throw new MultiErrorsException(errors, "Login fails");
		}
		
		CredentialsDTO credentials = null;
		if( GRANT_TYPE_PASSWORD.equals(signinDTO.getGrantType()) ) {
			String sha256hexPassword = Hashing.sha256()
					  .hashString(signinDTO.getPassword(), StandardCharsets.UTF_8)
					  .toString();
			// TODO: should get scopes along with checkSingnin
			User loggingUser = userDAO.checkSignin(signinDTO.getUsername(), sha256hexPassword);
			if( loggingUser == null ) {
				throw new BKDAServiceException(Constants.ERROR_LOGIN_FAILED, "Username or password incorrected");
			}
			
			credentials = authenticationService.createCredentials(loggingUser.getUsername(), 
					signinDTO.getGrantType(), "BKDA:*:*");
			
		} else if( GRANT_TYPE_CLIENT_CREDENTIALS.equals(signinDTO.getGrantType()) ) {
			// TODO: To be implemented
			throw new BKDAServiceException(Constants.ERROR_NOT_IMPLEMENTED, "This function has not been implemented yet!");
		}
		
		return credentials;
	}
	
	private List<ErrorContent> validateSignup(SignupDTO signinDTO) {
		List<ErrorContent> errors = new ArrayList<>();
		List<String> lstGrantTypes = Arrays.stream(grantTypes).collect(Collectors.toList());
		
		if( !lstGrantTypes.contains(signinDTO.getUserName()) ) {
			errors.add(new ErrorContent(Constants.ERROR_USERNAME_MISSING, "Username missing"));
		}
		
		if( StringUtils.isEmpty(signinDTO.getPassword()) ) {
			errors.add(new ErrorContent(Constants.ERROR_PASSWORD_MISSING, "Password missing"));
		}
		
		if( StringUtils.isEmpty(signinDTO.getFirstName()) ) {
			errors.add(new ErrorContent(Constants.ERROR_FIRSTNAME_MISSING, "First name missing"));
		}
			
		return errors;
	}
	
	private List<ErrorContent> validateLogin(SigninDTO signinDTO) {
		List<ErrorContent> errors = new ArrayList<>();
		List<String> lstGrantTypes = Arrays.stream(grantTypes).collect(Collectors.toList());
		
		if( !lstGrantTypes.contains(signinDTO.getGrantType()) ) {
			errors.add(new ErrorContent(Constants.ERROR_GRANT_TYPE_INVALID, "Grant type " + signinDTO.getGrantType() + " not supported "));
		}
		
		if( StringUtils.isEmpty(signinDTO.getPassword()) ) {
			errors.add(new ErrorContent(Constants.ERROR_PASSWORD_MISSING, "Password missing"));
		}
		
		if( StringUtils.isEmpty(signinDTO.getUsername()) ) {
			errors.add(new ErrorContent(Constants.ERROR_USERNAME_MISSING, "Username missing"));
		}
			
		return errors;
	}

}
