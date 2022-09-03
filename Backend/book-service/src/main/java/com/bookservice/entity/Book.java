package com.bookservice.entity;

import java.math.BigDecimal;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Entity

@Table(	name = "book", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "id")
	})
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userid;

	private Blob logo;
	@NotBlank(message = "Title must not be empty")
	private String title;
	@NotBlank(message = "Category must not be empty")
	private String category;
	
	@NotBlank(message = "Price must not be empty")
	private String price;
	
	@NotBlank(message = "Author name must be not empty")
	private String author;
	@NotBlank(message = "Publisher name must be not empty")
	private String publisher;
	@NotBlank(message = "Published date  must be not empty")
	private String published_date;
	private String active;
    private String content;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	public String getPublished_date() {
		return published_date;
	}

	public void setPublished_date(String published_date) {
		this.published_date = published_date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
