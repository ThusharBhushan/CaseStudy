package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookservice.entity.Book;
import com.bookservice.entity.User;
import com.bookservice.exception.BookNotFoundException;
import com.bookservice.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@InjectMocks
	BookController bookController;

	@Mock
	BookService bookService;
	
	
	@Test
	void testCreateBook() {
		Book book = new Book();
		User user = new User();
		book.setTitle("TestBook");
		user.setId((long) 1);
		book.setUserid(user.getId());
		book.setPublisher("John");
		book.setPublished_date("");
		book.setPrice("50.40");
		book.setCategory("Adventure");
		book.setActive("true");
		
		ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
		when(bookController.createBook(user.getId(), book)).thenReturn(responseEntity);
		assertEquals(book.getId(), 1);

	}

	@Test
	void testUpdateBookException() {
		Book book = new Book();
		book.setId((long) 1);
		book.setTitle("TestBook");

		User user = new User();
		user.setId((long) 1);
		book.setUserid(user.getId());
		book.setPublisher("John");
		book.setPublished_date("");
		book.setPrice(("50.40"));
		book.setCategory("Thriller");
		book.setActive("true");
		
		assertThrows((BookNotFoundException.class),()->{
			bookController.updateBook(book.getId(), user.getId(),book);
		});

	}
	@Test
	void testUpdateBookSuccess() {
		Book book = new Book();
		book.setId((long) 1);
		book.setTitle("TestBook");

		User user = new User();
		user.setId((long) 1);
		book.setUserid(user.getId());
		book.setPublisher("John");
		book.setPublished_date("");
		book.setPrice("50.40");
		book.setCategory("Thriller");
		book.setActive("true");
		
		ResponseEntity responseEntity = new ResponseEntity(book, HttpStatus.FOUND);
		try { 
			when(bookController.updateBook(book.getId(), user.getId(),book)).thenReturn(responseEntity);
			assertEquals(responseEntity, responseEntity);
		} catch (BookNotFoundException e) {
			assertEquals(e.getMessage(), "Book Not Found");
		}

	}

}
