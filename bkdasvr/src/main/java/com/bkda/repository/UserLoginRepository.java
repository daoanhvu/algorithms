package com.bkda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkda.model.User;

public interface UserLoginRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
}
