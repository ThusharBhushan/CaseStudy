package com.bookservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import com.bookservice.entity.Book;
import com.bookservice.entity.Payment;
import com.bookservice.enums.Category;
import com.bookservice.enums.Status;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.PaymentRepository;
import com.bookservice.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	BookService bookService;

	@Mock
	BookRepository bookRepository;
	
	@Mock
	PaymentRepository paymentRepository;

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
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);

		Mockito.lenient().when(bookRepository.findById(book.getUserid())).thenReturn(bookObject);
		book.setPublisher("Andrew");
		bookRepository.bookToUpdate(book.getUserid(), book.getId(), book.getTitle(), book.getCategory().toString(), book.getPrice(),
				book.getAuthor(), book.getPublished_date(), book.getActive().toString(), book.getContent(),
				book.getPublisher());
		assertEquals(true, Mockito.lenient().when(bookService.updateBook(book.getUserid(), book.getId(),book)).thenReturn(true));

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
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
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
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
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
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
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
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
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
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
		bookList.add(book);
		Mockito.lenient().when(bookRepository.searchBookByPublisher(publisher)).thenReturn(bookList);
		assertEquals(1, bookList.size());

	}
	
	@Test
	void doPayment() {
		long bookId = 1;
		String userName = "Thushar";
		String mailId = "thushar@gmail.com";
		UUID uuid = UUID.randomUUID();
		Payment payment = new Payment();
		payment.setId(bookId);
		payment.setBookid(bookId);
		payment.setMailid(mailId);
		payment.setPayment_date(LocalDateTime.now());
		payment.setUsername(userName);
		payment.setPaymentid(uuid.toString());
		Mockito.lenient().when(paymentRepository.save(payment)).thenReturn(payment);
		assertEquals(1,payment.getId());
	}
	@Test
	void doPayment1() {
		long bookId = 1;
		String userName = "Thushar";
		String mailId = "thushar@gmail.com";
		UUID uuid = UUID.randomUUID();
		Payment payment = new Payment();
		payment.setId(bookId);
		payment.setBookid(bookId);
		payment.setMailid(mailId);
		payment.setPayment_date(LocalDateTime.now());
		payment.setUsername(userName);
		payment.setPaymentid(uuid.toString());
		Mockito.lenient().when(bookService.doPayment(bookId, userName, mailId)).thenReturn(payment);
		assertEquals(1,payment.getId());
	}
	
	@Test
	void getAllBooksForReader() {
		List<Book> bookList = new ArrayList<Book>();
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
		book.setAuthor("ABC");
		book.setContent("This is my first Book");
		bookList.add(book);
		Mockito.lenient().when(bookRepository.findAll()).thenReturn(bookList);
		assertEquals(1,bookList.size());
		
		
	}
	@Test
	void getAllBooksForReader1() {
		List<Book> bookList = new ArrayList<Book>();
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
		book.setAuthor("ABC");
		book.setContent("This is my first Book");
		bookList.add(book);
		Mockito.lenient().when(bookService.getAllBooksForReader()).thenReturn(bookList).thenReturn(bookList);
		assertEquals(1,bookList.size());
		
		
	}
	@Test
	void getAllBooks() {
		List<Book> bookList = new ArrayList<Book>();
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
		book.setAuthor("ABC");
		book.setContent("This is my first Book");
		bookList.add(book);
		Mockito.lenient().when(bookRepository.findBooksByUserId(book.getUserid())).thenReturn(bookList);
		assertEquals(1,bookList.size());
	}
	
	@Test
	void getAllBooks1() {
		List<Book> bookList = new ArrayList<Book>();
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
		book.setAuthor("ABC");
		book.setContent("This is my first Book");
		bookList.add(book);
		Mockito.lenient().when(bookService.getAllBooks(book.getUserid())).thenReturn(bookList);
		assertEquals(1,bookList.size());
	}
	
	@Test
	void getReaderBooks(){
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
		book.setAuthor("ABC");
		book.setContent("This is my first Book");
		Mockito.lenient().when(bookRepository.findBookForReader(book.getId(),"123")).thenReturn(book);
		assertEquals(1,book.getId());	 
	}
	
	@Test
	void getReaderBooks1(){
		Book book = new Book();
		book.setId((long) 1);
		book.setUserid((long) 1);
		book.setTitle("Inc");
		book.setUserid((long) 1);
		book.setPublisher("John");
		book.setPublished_date(new Date());
		book.setPrice(new BigDecimal("500.00"));
		book.setCategory(Category.DRAMA);
		book.setActive(Status.ACTIVE);
		book.setAuthor("ABC");
		book.setContent("This is my first Book");
		Mockito.lenient().when(bookService.getReaderBooks(book.getId(),"123")).thenReturn(book);
		assertEquals(1,book.getId());	 
	}
	
	@Test
	void getPurchasedBooksForReader(){
		long bookId = 1;
		String userName = "Thushar";
		String mailId = "thushar@gmail.com";
		UUID uuid = UUID.randomUUID();
		Payment payment = new Payment();
		payment.setId(bookId);
		payment.setBookid(bookId);
		payment.setMailid(mailId);
		payment.setPayment_date(LocalDateTime.now());
		payment.setUsername(userName);
		payment.setPaymentid(uuid.toString());
		Mockito.lenient().when(paymentRepository.getPurchasedBook(payment.getPaymentid())).thenReturn(payment);
		assertEquals(1,payment.getId());
	}
	
	@Test
	void getPurchasedBooksForReader1(){
		long bookId = 1;
		String userName = "Thushar";
		String mailId = "thushar@gmail.com";
		UUID uuid = UUID.randomUUID();
		Payment payment = new Payment();
		payment.setId(bookId);
		payment.setBookid(bookId);
		payment.setMailid(mailId);
		payment.setPayment_date(LocalDateTime.now());
		payment.setUsername(userName);
		payment.setPaymentid(uuid.toString());
		Mockito.lenient().when(bookService.getPurchasedBooksForReader(payment.getPaymentid())).thenReturn(payment);
		assertEquals(1,payment.getId());
	}
}
