package com.bkda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkda.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
}
