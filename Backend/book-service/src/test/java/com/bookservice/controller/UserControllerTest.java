package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookservice.entity.User;
import com.bookservice.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	void testCreateUser() {
		
		User user = new User();
		
		
		
		when(userController.createAuthorAccount(user)).thenReturn("User Details registered successfully");
		userService.createAuthor(user);
		assertEquals(user.getId(), user.getId());
	}

}
