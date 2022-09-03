package com.bookservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.entity.User;
import com.bookservice.repository.UserRepository;
import com.bookservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/digitalbooks/author")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepo;
	@Autowired
	AuthenticationManager authentiactionManager;

	@PostMapping("/login")
	ResponseEntity login(@Valid @RequestBody User user) {
		Authentication authentication;
		authentication = authentiactionManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/signup")
	ResponseEntity createAuthorAccount(@Valid @RequestBody User user) {

		if (userRepo.existsByemail(user.getEmail())) {
			return ResponseEntity.badRequest().body("Email exist already!Please try with different mailid");
		}
		if (userRepo.existsByusername(user.getUsername())) {
			return ResponseEntity.badRequest().body("UserName exist already!Please try with different username");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		userService.createAuthor(user);

		return ResponseEntity.ok("User registered successfully");
	}

}
