package com.bookservice.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "book", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userid;
	private String logo;
	@NotBlank(message = "Title must not be empty")
	private String title;
	@NotBlank(message = "Category must not be empty")
	private String category;
	@DecimalMin(value = "1.0", message = "The decimal value can not be less than 1.00 digit ")
	@Digits(integer=4, fraction=2)
	private BigDecimal price;
	@NotBlank(message = "Author name must be not empty")
	private String author;
	@NotBlank(message = "Publisher name must be not empty")
	private String publisher;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date published_date;
	private String active;
	private String content;
}
