package com.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.userservice.entity.User;
import com.userservice.repository.UserRepository;

public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void createAuthor(User user) {
		userRepository.save(user);
	}

}
