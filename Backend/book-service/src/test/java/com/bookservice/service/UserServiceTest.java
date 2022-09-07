package com.bookservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookservice.entity.User;
import com.bookservice.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@InjectMocks
	UserService userService;
	@Mock
	UserRepository userRepository;
	
	@Test
	void testCreateAuthorMethod() {
		User user = new User();
		user.setUsername("Elon");
		user.setEmail("abc@gmail.com");
		user.setPassword("123");
		user.setGender("Male");
		user.setUserrole("Author");
		Mockito.lenient().when(userRepository.save(user)).thenReturn(user);
		assertEquals(user.getEmail(), "abc@gmail.com");
	}
	

}
