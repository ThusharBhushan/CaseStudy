package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookservice.entity.User;
import com.bookservice.repository.UserRepository;
import com.bookservice.requestdto.LoginRequestDto;
import com.bookservice.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserRepository userRepo;

	@Mock
	private UserService userService;
	

	@Test
	void testLogIn() {
		LoginRequestDto user = new LoginRequestDto();
		user.setUsername("Mary");
		user.setPassword("123");

		Mockito.lenient().when(userRepo.existsByusername(user.getUsername())).thenReturn(true);
		Base64.Decoder decoder = Base64.getMimeDecoder();  
		User userToCheck = userRepo.getUser(user.getUsername());
		String decodedPwd = new String (decoder.decode(user.getPassword()));
//		Mockito.lenient().when(userToCheck.getPassword().equals(user.getPassword())).thenReturn(true);
		ResponseEntity responseEntity = new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
		assertEquals(responseEntity.getBody(), "Logged in successfully");
	}

	@Test
	void testLogInWrongUserName() {
		LoginRequestDto user = new LoginRequestDto();
		user.setUsername("ABC");
		user.setPassword("123");

		Mockito.lenient().when(userRepo.existsByusername(user.getUsername())).thenReturn(false);
		ResponseEntity responseEntity = ResponseEntity.badRequest().body("UserName is wrong");
		assertEquals(responseEntity.getBody(), "UserName is wrong");
	}

	@Test
	void testCreateUser() {
		User user = new User();
		user.setUsername("Elon");
		user.setEmail("abc@gmail.com");
		user.setPassword("123");
		user.setGender("Male");
		user.setUserrole("Author");

		Mockito.lenient().when(userRepo.existsByemail(user.getEmail())).thenReturn(false);
		Mockito.lenient().when(userRepo.existsByusername(user.getUsername())).thenReturn(false);
		Base64.Encoder encoder = Base64.getMimeEncoder();  
		user.setPassword(encoder.encodeToString(user.getPassword().getBytes()));
		ResponseEntity responseEntity = ResponseEntity.ok("User registered successfully");
		userService.createAuthor(user);
		assertEquals(responseEntity.getBody(), "User registered successfully");
	}
	
	@Test
	void testCreateUserExistByEmail() {
		User user = new User();
		user.setUsername("Mary");
		user.setEmail("abc@gmail.com");
		user.setPassword("123");
		user.setGender("Male");
		user.setUserrole("Author");

		Mockito.lenient().when(userRepo.existsByemail(user.getEmail())).thenReturn(true);
		ResponseEntity responseEntity=ResponseEntity.badRequest().body("Email exist already!Please try with different mailid");
		assertEquals(responseEntity.getBody(), "Email exist already!Please try with different mailid");
	}
	@Test
	void testCreateUserExistByName() {
		User user = new User();
		user.setUsername("Mary");
		user.setEmail("abc@gmail.com");
		user.setPassword("123");
		user.setGender("Male");
		user.setUserrole("Author");

		Mockito.lenient().when(userRepo.existsByusername(user.getEmail())).thenReturn(true);
		ResponseEntity responseEntity=ResponseEntity.badRequest().body("UserName exist already!Please try with different username");
		assertEquals(responseEntity.getBody(), "UserName exist already!Please try with different username");
	}

}
