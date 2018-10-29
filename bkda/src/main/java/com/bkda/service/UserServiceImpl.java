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
import com.bkda.model.Scope;
import com.bkda.model.User;
import com.bkda.util.DataHelper;
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
	public Page<User> search(UserDTO userDto, Pageable paging) {
		return userDAO.search(userDto.getUsername(), userDto.getGroupId(), userDto.getFirstName(), 
				userDto.getLastName(), userDto.getSex(), paging);
	}

	@Override
	public boolean isUserExist(long id) {
		return userDAO.isUserExist(id);
	}
	
	@Override
	public User updateUser(User user) {
		List<ErrorContent> errors = validateUpdate(user);
		if(errors.size() > 0) {
			throw new MultiErrorsException(errors, "Update fails");
		}
		return userDAO.updateUser(user);
	}

	@Override
	public User signup(SignupDTO signupDto) {
		List<ErrorContent> errors = validateSignup(signupDto);
		if( errors.size() > 0 ) {
			throw new MultiErrorsException(errors, "Login fails");
		}
		User user = new User();
		user.setUsername(signupDto.getUsername());
		String sha256hexPassword = Hashing.sha256()
				  .hashString(signupDto.getPassword(), StandardCharsets.UTF_8)
				  .toString();
		user.setPassword(sha256hexPassword);
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastName());
		user.setSex(signupDto.getSex()==null?'F':signupDto.getSex());
		user.setStartDate( Date.from(Instant.now(Clock.systemUTC())) );
		user.setStatus(User.UserStatus.INACTIVE);
//		user = userDAO.saveUser(user);
		Scope userScope = new Scope("All", "User", "user");
		userScope.setUser(user);
		user.getScopes().add(userScope);
//		userDAO.saveScope(userScope);
		user = userDAO.saveUser(user);
		// TODO: write user log
		// This should be implemented with Aspect programming
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
			
			credentials = authenticationService.createCredentials(loggingUser, 
					signinDTO.getGrantType());
			credentials.setUserId(loggingUser.getId());
			credentials.setGrantType(signinDTO.getGrantType());
			credentials.setTokenType("Bearer");
			// this token will be expired in 60 minutes
			credentials.setExpiresIn(3600000L);
			
		} else if( GRANT_TYPE_CLIENT_CREDENTIALS.equals(signinDTO.getGrantType()) ) {
			// TODO: To be implemented
			throw new BKDAServiceException(Constants.ERROR_NOT_IMPLEMENTED, "This function has not been implemented yet!");
		}
		
		return credentials;
	}
	
	private List<ErrorContent> validateSignup(SignupDTO signupDTO) {
		List<ErrorContent> errors = new ArrayList<>();
		
		if( StringUtils.isBlank(signupDTO.getUsername()) ) {
			errors.add(new ErrorContent(Constants.ERROR_USERNAME_MISSING, "Username missing"));
		} else if( !DataHelper.checkEmail(signupDTO.getUsername()) ) {
			errors.add(new ErrorContent(Constants.ERROR_USERNAME_MISSING, "Username is not a valid email"));
		}
		
		if( StringUtils.isEmpty(signupDTO.getPassword()) ) {
			errors.add(new ErrorContent(Constants.ERROR_PASSWORD_MISSING, "Password missing"));
		}
		
		if( StringUtils.isEmpty(signupDTO.getFirstName()) ) {
			errors.add(new ErrorContent(Constants.ERROR_FIRSTNAME_MISSING, "First name missing"));
		}
			
		return errors;
	}
	
	private List<ErrorContent> validateUpdate(User user) {
		List<ErrorContent> errors = new ArrayList<>();
		
		if( StringUtils.isBlank(user.getUsername()) ) {
			errors.add(new ErrorContent(Constants.ERROR_USERNAME_MISSING, "Username missing"));
		} else if( !DataHelper.checkEmail(user.getUsername()) ) {
			errors.add(new ErrorContent(Constants.ERROR_USERNAME_MISSING, "Username is not a valid email"));
		}
		
		if( StringUtils.isEmpty(user.getPassword()) ) {
			errors.add(new ErrorContent(Constants.ERROR_PASSWORD_MISSING, "Password missing"));
		}
		
		if( StringUtils.isEmpty(user.getFirstName()) ) {
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
