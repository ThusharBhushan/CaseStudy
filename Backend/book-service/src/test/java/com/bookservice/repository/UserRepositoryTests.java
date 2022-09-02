package com.bookservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.bookservice.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase
@Rollback(true)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		
		User user = new User();
		
		User savedUser=userRepo.save(user);
		User existUser=entityManager.find(User.class, savedUser.getId());
		assertThat(user.getId().equals(existUser.getId()));
		
	}

}
