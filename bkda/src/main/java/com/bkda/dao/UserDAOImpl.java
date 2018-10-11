package com.bkda.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bkda.model.User;

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
	public long saveUser(User user) {		
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
	public Page<User> search(String username, String firstname, 
							String lastname, Character sex, Pageable paging) {
		
		List<User> result =  null;
		String whereClause = "where ( ( :username = null ) or ( u.username like :username ) ) "
				+ "and ( ( :firstname = null ) or (u.firstName like :firstname) ) "
				+ "and ( ( :lastname = null ) or (u.lastName like :lastname) ) ";
		String strQuery = "select u from User ";
		String countQueryStr = "select count(*) from User ";
		
		Query countQuery = this.entityManager.createQuery(countQueryStr + whereClause);
		countQuery.setParameter("username", username);
		countQuery.setParameter("firstName", firstname);
		countQuery.setParameter("lastName", lastname);
		long total = (Long)countQuery.getSingleResult();
		
		Query query = this.entityManager.createQuery(strQuery + whereClause);
		query.setParameter("username", username);
		query.setParameter("firstName", firstname);
		query.setParameter("lastName", lastname);
		query.setFirstResult((int)paging.getOffset());
		query.setMaxResults(paging.getPageSize());
		result = (List<User>)query.getResultList();
		Page<User> page = new PageImpl<>(result, paging, total);
		return page;
	}

	@Override
	public boolean isUserExist(long id) {
//		System.out.println("isUserExist: " + id);
		User u = entityManager.find(User.class, id);
		return (u != null);
//		return entityManager.getReference(User.class, id) != null;
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "UPDATE users set firstname=:firstname, "
				+ "lastname=:lastname, email=:email, phonenumber=:phonenumber where id=:userid";
		Query updateQ = entityManager.createNativeQuery(sql);
		updateQ.setParameter("userid",user.getId());
		updateQ.setParameter("firstname",user.getFirstName());
		updateQ.setParameter("lastname",user.getLastName());
		updateQ.setParameter("email",user.getEmail());
		updateQ.setParameter("phonenumber",user.getPhoneNumber());
		return updateQ.executeUpdate()>0?true:false;
	}

	@Override
	public User checkSignin(String username, String hashedPassword) {
		String strQuery = "from User "
				+ "where username = :username and password = :password";
		Query query = this.entityManager.createQuery(strQuery);
		query.setParameter("username", username);
		query.setParameter("password", hashedPassword);
		return (User) query.getSingleResult();
	}
}
