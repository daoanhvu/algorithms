package com.bkda.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bkda.entity.User;
@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findUserById(int id) {
		User user = entityManager.find(User.class, id);
		return user;
	}

	@Override
	public int addNewUser(User user) {
		if(user.getId() <= 0)
			user.setStartDate(new Date());
		
		this.entityManager.persist(user);
		return user.getId();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUsersByName(String name) {
		List<User> result;
		
		String strQuery = "SELECT u FROM User WHERE u.firstname like '%:name%' OR u.lastname like '%:name%'";
		Query query = this.entityManager.createQuery(strQuery);
		query.setParameter("name", name);
		
		result = (List<User>)query.getResultList();
		
		return result;
	}

	@Override
	public List<User> allUsers() {
		List<User> result;
		String strQuery = "from User";
		Query query = this.entityManager.createQuery(strQuery);
		result = (List<User>)query.getResultList();
		return result;
	}

	@Override
	public boolean isUserExist(int id) {
//		System.out.println("isUserExist: " + id);
		User u = entityManager.find(User.class, id);
		return (u != null);
//		return entityManager.getReference(User.class, id) != null;
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "UPDATE users set firstname=:firstname, lastname=:lastname, email=:email, phonenumber=:phonenumber where id=:userid";
		Query updateQ = entityManager.createNativeQuery(sql);
		updateQ.setParameter("userid",user.getId());
		updateQ.setParameter("firstname",user.getFirstName());
		updateQ.setParameter("lastname",user.getLastName());
		updateQ.setParameter("email",user.getEmail());
		updateQ.setParameter("phonenumber",user.getPhoneNumber());
		return updateQ.executeUpdate()>0?true:false;
	}

}
