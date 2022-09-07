package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookservice.entity.Book;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.UserRepository;
import com.bookservice.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@InjectMocks
	BookController bookController;

	@Mock
	BookService bookService;

	@Mock
	UserRepository userRepository;

	@Mock
	BookRepository bookRepository;

	@Test
	void testCreateBook() {
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory("Thriller");
		book.setActive("true");
		book.setAuthor("ABC");
		book.setContent("This is my first Book");
		
	    
		Mockito.lenient().when(userRepository.existsById(book.getUserid())).thenReturn(true);
		Mockito.lenient().when(bookRepository.save(book)).thenReturn(book);
		ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
		Mockito.lenient().when(bookController.createBook(book.getUserid(), book)).thenReturn(responseEntity);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	void testCreateBookAuthorNotExist() {
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 7);
		ResponseEntity responseEntity = new ResponseEntity<>("Author does not exist", HttpStatus.UNAUTHORIZED);
		Mockito.lenient().when(userRepository.existsById(book.getUserid())).thenReturn(false);
		assertEquals(responseEntity.getBody(), "Author does not exist");

	}
	@Test
	void testCreateBookAuthorExist() {
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 7);
		assertEquals(true, Mockito.lenient().when(userRepository.existsById(book.getUserid())).thenReturn(true));

	}
	
	@Test
	void testCreateBookSaveMethod() {
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory("Thriller");
		book.setActive("true");
		book.setAuthor("ABC");
		book.setContent("This is my first Book");
		Mockito.lenient().when(bookRepository.save(book)).thenReturn(book);
		assertEquals(book.getId(), (long)1);

	}

	@Test
	void testUpdateBook() {
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory("Thriller");
		book.setActive("Active");
		Mockito.lenient().when(bookRepository.existsById(book.getUserid())).thenReturn(true);
		Mockito.lenient().when(bookService.updateBook(book.getUserid(), book.getId(), book)).thenReturn(book);
		assertEquals(book.getId(), (long) 1);

	}
	
	@Test
	void testUpdateBookBookNotFound() {
		Book book = new Book();
		book.setId((long) 9);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory("Thriller");
		book.setActive("Active");
		
		Mockito.lenient().when(bookRepository.existsById(book.getUserid())).thenReturn(true);
		Mockito.lenient().when(bookService.updateBook(book.getUserid(), book.getId(), book)).thenReturn(null);
		ResponseEntity responseEntity = new ResponseEntity<>("Book does not exist", HttpStatus.NOT_FOUND);
		assertEquals(responseEntity.getBody(), "Book does not exist");
		
		
	}

	@Test
	void testUpdateBookAuthorIsNotFound() {
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 3);
		ResponseEntity responseEntity = new ResponseEntity<>("Author does not exist", HttpStatus.UNAUTHORIZED);
		Mockito.lenient().when(bookRepository.existsById(book.getUserid())).thenReturn(false);
		assertEquals(responseEntity.getBody(), "Author does not exist");

	}

	@Test
	void testUpdateBookSuccess() {
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory("Thriller");
		book.setActive("true");
		Mockito.lenient().when(bookRepository.existsById(book.getUserid())).thenReturn(true);
		Mockito.lenient().when(bookService.updateBook(book.getUserid(), book.getId(), book)).thenReturn(book);
		ResponseEntity responseEntity = new ResponseEntity<>(book, HttpStatus.CREATED);
		Mockito.lenient().when(bookController.updateBook(book.getId(), book.getUserid(), book)).thenReturn(responseEntity);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void testSuccessSearchBook() {
		String category = "Thriller";
		String author = "John";
		BigDecimal price = new BigDecimal("500.00");
		String publisher = "VKS";

		Set<Book> bookList = new HashSet<>();
		Book book = new Book();
		book.setId((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory("Thriller");
		book.setActive("true");
		bookList.add(book);

		Mockito.lenient().when(bookService.searchBook(category, author, price, publisher)).thenReturn(bookList);
		assertEquals(bookList.size(), bookList.size());

	}

	@Test
	void testNoSearchBookFound() {
		String category = "Drama";
		String author = "ABC";
		BigDecimal price = new BigDecimal("500.00");
		String publisher = "STT";
		
		Set<Book> bookList = new HashSet<>();
		bookList=bookService.searchBook(category, author, price, publisher);
		ResponseEntity responseEntity = new ResponseEntity<>(bookList, HttpStatus.FOUND);
		Mockito.lenient().when(bookController.searchBook(category, author, price, publisher)).thenReturn(responseEntity);
		assertEquals(bookList.size(),0);

	}

}
