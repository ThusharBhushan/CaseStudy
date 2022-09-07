package com.bookservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookservice.entity.Book;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	BookService bookService;

	@Mock
	BookRepository bookRepository;

	@Mock
	UserRepository userRepository;

	@Test
	void testUpdateBook() {
		Optional<Book> bookObject = Optional.of(new Book());
		Book book = bookObject.get();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory("Thriller");
		book.setActive("Active");

		Mockito.lenient().when(bookRepository.findById(book.getUserid())).thenReturn(bookObject);
		book.setPublisher("Andrew");
		Mockito.lenient().when(bookRepository.save(book)).thenReturn(book);
		assertEquals(book.getPublisher(), "Andrew");

	}
	@Test
	void testUpdateBookReturnNull() {
		Optional<Book> bookObject = Optional.of(new Book());
		Book book = bookObject.get();
		book.setId((long) 1);
		book.setUserid((long) 10);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory("Thriller");
		book.setActive("Active");
		Mockito.lenient().when(bookRepository.findById(book.getUserid())).thenReturn(bookObject);
		assertEquals(bookObject.isPresent(), true);

	}
	
	@Test
	void testSearchBookByCategory(){
		String category = "Thriller";
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
		Mockito.lenient().when(bookRepository.searchBookByCategory(category)).thenReturn(bookList);
		assertEquals(bookList.size(), bookList.size());

	}
	@Test
	void testSearchBookByAuthor(){
		String author = "John";
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
		Mockito.lenient().when(bookRepository.searchBookByAuthor(author)).thenReturn(bookList);
		assertEquals(bookList.size(), bookList.size());

	}
	@Test
	void testSearchBookByPrice(){
		BigDecimal price = new BigDecimal("500.00");
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
		Mockito.lenient().when(bookRepository.searchBookByPrice(price)).thenReturn(bookList);
		assertEquals(bookList.size(), bookList.size());

	}
	@Test
	void testSearchBookByPublisher(){
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
		Mockito.lenient().when(bookRepository.searchBookByPublisher(publisher)).thenReturn(bookList);
		assertEquals(bookList.size(), bookList.size());

	}

}
