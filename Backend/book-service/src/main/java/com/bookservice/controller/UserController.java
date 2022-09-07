package com.bookservice.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.entity.User;
import com.bookservice.repository.UserRepository;
import com.bookservice.requestdto.LoginRequestDto;
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
	
	

	@PostMapping("/login")
	ResponseEntity login(@Valid @RequestBody LoginRequestDto user) {
		Base64.Decoder decoder = Base64.getMimeDecoder();  
		if (userRepo.existsByusername(user.getUsername())) {
			User existingUser = userRepo.getUser(user.getUsername());
			String decodedPwd = new String (decoder.decode(existingUser.getPassword()));
			if (decodedPwd.equals(user.getPassword())) {
				return new ResponseEntity<>("Logged in successfully",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Password is wrong",HttpStatus.OK);
			}
		}
		return ResponseEntity.badRequest().body("UserName is wrong");
	}

	@PostMapping("/signup")
	ResponseEntity createAuthorAccount(@Valid @RequestBody User user) {
		 Base64.Encoder encoder = Base64.getMimeEncoder();  
		if (userRepo.existsByemail(user.getEmail())) {
			return ResponseEntity.badRequest().body("Email exist already!Please try with different mailid");
		}
		if (userRepo.existsByusername(user.getUsername())) {
			return ResponseEntity.badRequest().body("UserName exist already!Please try with different username");
		}
		user.setPassword(encoder.encodeToString(user.getPassword().getBytes()));
		userService.createAuthor(user);

		return ResponseEntity.ok("User registered successfully");
	}

}
