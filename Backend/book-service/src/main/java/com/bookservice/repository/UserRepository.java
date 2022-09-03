package com.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "Select u from user u where u.email=:email", nativeQuery = true)
	public User findByEmail(@Param("email") String email);


	 Boolean existsByusername(String userName);

	 Boolean existsByemail(String email);

}
