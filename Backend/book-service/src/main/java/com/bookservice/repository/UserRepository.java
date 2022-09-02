package com.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookservice.entity.User;

public interface UserRepository  extends JpaRepository<User,Long>{
	
	@Query(value ="Select u from user u where u.email=:email", nativeQuery = true )
	public User findByEmail(@Param("email") String email);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
