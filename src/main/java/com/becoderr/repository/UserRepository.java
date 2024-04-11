package com.becoderr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoderr.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
	public User findByVerificationCode(String code);
}
