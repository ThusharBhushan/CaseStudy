package com.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.entity.User;
import com.userservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/v1/digitalbooks/author/signup")
	@ResponseStatus(code=HttpStatus.CREATED)
	void createAuthorAccount(@Valid @RequestBody User user) {
		userService.createAuthor(user);
	}

}
