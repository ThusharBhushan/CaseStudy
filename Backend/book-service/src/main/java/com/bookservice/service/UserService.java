package com.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookservice.entity.User;
import com.bookservice.repository.UserRepository;

public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void createAuthor(User user) {
		userRepository.save(user);
	}

}
