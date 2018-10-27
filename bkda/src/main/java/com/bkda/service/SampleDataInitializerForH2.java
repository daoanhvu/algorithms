package com.bkda.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.bkda.dto.GroupDTO;
import com.bkda.dto.SignupDTO;
import com.bkda.model.Group;
import com.bkda.model.User;
import com.bkda.model.UserGroup;

/**
 * This is used for initializing sample data for demo and just be used
 * with profiles H2 only!!!!
 * TODO: Remove this class in production!
 * @author davu
 *
 */
@Component
@Profile(value = {"h2"})
public class SampleDataInitializerForH2 {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@PostConstruct
	public void initialize() {
		initUsers();
	}
	
	private void initUsers() {
		SignupDTO signup = new SignupDTO();
		signup.setFirstName("Admin");
		signup.setLastName("System");
		signup.setUsername("admin@bkda.com");
		signup.setPassword("123456");
		userService.signup(signup);
		
		signup.setFirstName("Dao");
		signup.setLastName("Vu");
		signup.setUsername("anhvu@bkda.com");
		signup.setPassword("123456");
		User vu = userService.signup(signup);
		
		GroupDTO groupDto = new GroupDTO();
		groupDto.setDescription("Chao mung ban den voi nhom cua Vu");
		groupDto.setUserId(vu.getId());
		groupDto.setName("Phat trien ung dung Android");
		Group vuGroup = groupService.createGroup(groupDto);
		
		signup.setFirstName("Huong");
		signup.setLastName("Ng");
		signup.setUsername("huong@bkda.com");
		signup.setPassword("123456");
		User huong = userService.signup(signup);
		groupService.addUserToGroup(huong.getId(), vuGroup.getId(), UserGroup.UserGroupRole.MEMBER);
		
		signup.setFirstName("Vo");
		signup.setLastName("Anh");
		signup.setUsername("anh@bkda.com");
		signup.setPassword("123456");
		User tuanAnh = userService.signup(signup);
		groupDto = new GroupDTO();
		groupDto.setName("Nhom quan ly he thong");
		groupDto.setDescription("Chao mung ban den voi nhom cua chung toi. Nhom nay chuyen trach cong viec phat trien phan mem va he thong thong tin.");
		groupDto.setUserId(tuanAnh.getId());
		Group tuanAnhGroup = groupService.createGroup(groupDto);
		
		groupService.addUserToGroup(vu.getId(), tuanAnhGroup.getId(), UserGroup.UserGroupRole.MEMBER);
		
		signup.setFirstName("Hieu");
		signup.setLastName("PT");
		signup.setUsername("pthieu@bkda.com");
		signup.setPassword("123456");
		User hieu = userService.signup(signup);
		groupService.addUserToGroup(hieu.getId(), tuanAnhGroup.getId(), UserGroup.UserGroupRole.MEMBER);
		
		signup.setFirstName("Vong");
		signup.setLastName("Hoang");
		signup.setUsername("vong@bkda.com");
		signup.setPassword("123456");
		User vong = userService.signup(signup);
		groupService.addUserToGroup(vong.getId(), tuanAnhGroup.getId(), UserGroup.UserGroupRole.ADMIN);
		
		signup.setFirstName("Dao");
		signup.setLastName("Nguyen Minh Nhat");
		signup.setUsername("minhnhat@bkda.com");
		signup.setPassword("123456");
		User nhat = userService.signup(signup);
		groupService.addUserToGroup(nhat.getId(), vuGroup.getId(), UserGroup.UserGroupRole.MEMBER);
		
		signup.setFirstName("Dao");
		signup.setLastName("Nguyen Duy An");
		signup.setUsername("duyan@bkda.com");
		signup.setPassword("123456");
		User an = userService.signup(signup);
		groupService.addUserToGroup(an.getId(), vuGroup.getId(), UserGroup.UserGroupRole.MEMBER);
	}
}
