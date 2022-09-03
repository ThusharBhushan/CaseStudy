package com.bookservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "email" })

})

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Username must not be empty")
	private String username;
	@NotBlank(message = "Password must not be empty")
	private String password;
	@NotBlank(message = "Email must not be empty")
	private String email;
	@NotBlank(message = "Gender must not be empty")
	private String gender;
	@NotBlank(message = "Role must be either Reader/Author")
	private String userrole;

	public User() {
		// TODO Auto-generated constructor stub
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}





	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public String getUserrole() {
		return userrole;
	}


	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
