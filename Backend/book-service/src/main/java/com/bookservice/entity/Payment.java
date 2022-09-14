package com.bookservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "payment", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Username must not be empty")
	private String username;
	@NotBlank(message = "MailId must not be empty")
	private String mailid;
	private String paymentid;
	private Long bookid;
	private LocalDateTime payment_date;

}
