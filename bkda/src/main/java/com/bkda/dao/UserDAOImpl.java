package com.bkda.dao;

import java.math.BigInteger;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bkda.dto.GroupDTO;
import com.bkda.model.Group;
import com.bkda.model.GroupMember;
import com.bkda.model.Scope;
import com.bkda.model.ToDo;
import com.bkda.model.User;
import com.bkda.model.UserGroup;

@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findUserById(long id) {
		User user = entityManager.find(User.class, id);
		return user;
	}

	@Transactional
	@Override
	public User saveUser(User user) {		
		this.entityManager.persist(user);
		return user;
	}
	
	@Override
	public List<User> getUsersByName(String name) {
		List<User> result;
		String strQuery = "select u from User u where u.firstname like CONCAT('%',:name,'%') "
				+ "or u.lastname like CONCAT('%', :name, '%')";
		TypedQuery<User> query = this.entityManager.createQuery(strQuery, User.class);
		query.setParameter("name", name);
		result = query.getResultList();
		return result;
	}
	
	@Override
	public Page<User> search(String username, Long groupId, String firstname, 
			String lastname, Character sex, Pageable paging) {

		List<User> result =  null;
		String whereClause = "where ((:username IS NULL) or (u.username like CONCAT('%',:username,'%'))) "
		+ "and ((:groupId IS NULL) or (g.id = :groupId)) "
		+ "and ((:firstname IS NULL) or (u.firstName like CONCAT('%',:firstname,'%'))) "
		+ "and ((:lastname IS NULL) or (u.lastName like CONCAT('%',:lastname,'%'))) "
		+ "and u.status != com.bkda.model.User$UserStatus.DELETED";
		String strQuery = "select distinct u from User u join u.groups g ";
		String countQueryStr = "select count(distinct u) from User u join u.groups g ";
		
		Query countQuery = this.entityManager.createQuery(countQueryStr + whereClause);
		countQuery.setParameter("username", username);
		countQuery.setParameter("groupId", groupId);
		countQuery.setParameter("firstname", firstname);
		countQuery.setParameter("lastname", lastname);
		Long total = (Long)countQuery.getSingleResult();
		
		TypedQuery<User> query = this.entityManager.createQuery(strQuery + whereClause, User.class);
		query.setParameter("username", username);
		query.setParameter("groupId", groupId);
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname);
		query.setFirstResult(paging.getOffset());
		query.setMaxResults(paging.getPageSize());
		result = query.getResultList();
		return new PageImpl<User>(result, paging, total);
	}

	@Override
	public boolean isUserExist(long id) {
		User u = entityManager.find(User.class, id);
		return (u != null);
	}

	@Override
	public User updateUser(User user) {
		return entityManager.merge(user);
	}

	@Override
	public User checkSignin(String username, String hashedPassword) {
		String strQuery = "from User "
				+ "where username = :username and password = :password "
				+ "and status != com.bkda.model.User$UserStatus.DELETED";
		TypedQuery<User> query = this.entityManager.createQuery(strQuery, User.class);
		query.setParameter("username", username);
		query.setParameter("password", hashedPassword);
		List<User> results = query.getResultList();
		return results.size() > 0 ? results.get(0) : null;
	}

	@Transactional
	@Override
	public Scope saveScope(Scope scope) {
		this.entityManager.persist(scope);
		return scope;
	}

	@Override
	public Group getGroupByUserId(long uid) {
		String strQuery = "from Group g join fetch g.owner where g.owner.id = :uid";
		TypedQuery<Group> query = this.entityManager.createQuery(strQuery, Group.class);
		query.setParameter("uid", uid);
		List<Group> lst = query.getResultList();
		return lst.size() > 0 ? lst.get(0) : null;
	}

	@Transactional
	@Override
	public Group createGroupByUser(GroupDTO groupdto) {
		long uid = groupdto.getUserId();
		String sql1 = "from Group g where g.owner.id = :uid";
		TypedQuery<Group> query1 = this.entityManager.createQuery(sql1, Group.class);
		query1.setParameter("uid", uid);
		List<Group> groups = query1.getResultList();
		if(groups.size() > 0) {
			return groups.get(0);
		}
		
		User u = entityManager.find(User.class, uid);
		Date createdTime = Date.from(Instant.now(Clock.systemUTC()));
		Group newGroup = new Group();
		newGroup.setCreatedTime(createdTime);
		newGroup.setName(groupdto.getName());
		newGroup.setDescription(groupdto.getDescription());
		newGroup.setOwner(u);
		
		UserGroup userGroup = new UserGroup();
		userGroup.setRole(UserGroup.UserGroupRole.OWNER);
		userGroup.setUser(u);
		userGroup.setJoinTime(createdTime);
		u.addToGroup(userGroup);
		
		newGroup.addMember(userGroup);
		
		this.entityManager.persist(newGroup);
		
		return newGroup;
	}
	
	@Override
	public Page<User> getGroupUsers(long groupId, Pageable paging) {
		List<User> result =  null;
		String whereClause = "where g.group.id = :groupId "
		+ "and u.status != com.bkda.model.User$UserStatus.DELETED";
		String strQuery = "select u from UserGroup g join g.user u ";
		String countQueryStr = "select count(u) from UserGroup g join g.user u ";
		
		Query countQuery = this.entityManager.createQuery(countQueryStr + whereClause);
		countQuery.setParameter("groupId", groupId);
		Long total = (Long)countQuery.getSingleResult();
		
		TypedQuery<User> query = this.entityManager.createQuery(strQuery + whereClause, User.class);
		query.setParameter("groupId", groupId);
		query.setFirstResult(paging.getOffset());
		query.setMaxResults(paging.getPageSize());
		result = query.getResultList();
		return new PageImpl<User>(result, paging, total);
	}
	
	@Override
	public Page<GroupMember> getGroupMembers(long groupId, Pageable paging) {
		List<GroupMember> result =  new ArrayList<>();
		String strQuery = "select u.objectid, u.username, u.firstName, "
				+ "u.lastName, ug.role, ug.joinTime from users as u inner join "
				+ "users_groups as ug on u.objectid = ug.user_objectid "
				+ "where ug.group_objectid = :groupId and u.status != 3";
		String countQueryStr = "select count(*) from users as u "
				+ "inner join users_groups as ug on u.objectid = ug.user_objectid "
				+ "where ug.group_objectid = :groupId and u.status != 3";
		
		Query countQuery = this.entityManager.createNativeQuery(countQueryStr);
		countQuery.setParameter("groupId", groupId);
		long total = ((BigInteger)countQuery.getSingleResult()).longValue();
		
		Query query = this.entityManager.createNativeQuery(strQuery);
		query.setParameter("groupId", groupId);
		query.setFirstResult(paging.getOffset());
		query.setMaxResults(paging.getPageSize());
		List<Object[]> list = query.getResultList();
		result = list.stream().map(obj -> new GroupMember(obj)).collect(Collectors.toList());
		return new PageImpl<GroupMember>(result, paging, total);
	}

	@Override
	public Group findGroupById(long id) {
		Group group = entityManager.find(Group.class, id);
		return group;
	}
	
	@Transactional
	@Override
	public UserGroup addUserToGroup(long userId, long groupId, UserGroup.UserGroupRole role) {
		Group group = entityManager.find(Group.class, groupId);
		User user = entityManager.find(User.class, userId);
		UserGroup userGroup = new UserGroup();
		userGroup.setRole(role);
		userGroup.setUser(user);
		userGroup.setJoinTime(Date.from(Instant.now(Clock.systemUTC())));
		user.addToGroup(userGroup);
		group.addMember(userGroup);
		this.entityManager.persist(userGroup);
		return userGroup;
	}
	
	@Override
	public List<Group> getUserGroups(long uid) {
		String strQuery = "select distinct g.group from UserGroup g where "
				+ "g.user.id = :uid";
		TypedQuery<Group> query = this.entityManager
				.createQuery(strQuery, Group.class);
		query.setParameter("uid", uid);
		return query.getResultList();
	}
	
	/*======================================================================*/
	@Transactional
	@Override
	public ToDo saveToDo(ToDo todo) {		
		this.entityManager.persist(todo);
		return todo;
	}
	
	@Override
	public List<ToDo> getToDoListOf(long userId) {
		String queryStr = "from ToDo where destination.id = :userid "
				+ "and status = com.bkda.model.ToDo$ToDoStatus.PENDING";
		TypedQuery<ToDo> query = this.entityManager.createQuery(queryStr, ToDo.class);
		query.setParameter("userid", userId);
		return query.getResultList();
	}
}
