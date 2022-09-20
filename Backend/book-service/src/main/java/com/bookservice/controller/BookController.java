package com.bookservice.controller;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.entity.Book;
import com.bookservice.entity.Payment;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.UserRepository;
import com.bookservice.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/digitalbooks/")
public class BookController extends BaseController {

	@Autowired
	BookService bookService;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("allbooks/{authorId}")
	Iterable<Book> getAllBooks(@Valid @PathVariable("authorId") Long id) {
		return bookService.getAllBooks(id);
	}
	
	@GetMapping("reader/{bookId}/payment/{paymentId}")
	Book getReaderBooks(@Valid @PathVariable("bookId") Long bookId, @PathVariable("paymentId") String paymentId) {
		return bookService.getReaderBooks(bookId,paymentId);
	}

	@GetMapping("reader/allbooks")
	Iterable<Book> getAllBooksForReader() {
		return bookService.getAllBooksForReader();
	}
	
	@GetMapping("reader/payment/{paymentId}")
	Payment getPurchasedBooksForReader(@Valid @PathVariable("paymentId") String paymentId) {
		return bookService.getPurchasedBooksForReader(paymentId);
	}

	@PostMapping("payment/book/{bookId}/username/{username}/mailId/{mailId}")
	ResponseEntity<?> doPaymentForBook(@Valid @PathVariable("bookId") Long bookId, @PathVariable("username") String userName,
			@PathVariable("mailId") String mailId) {
		Payment payment = bookService.doPayment(bookId,userName,mailId);
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

	@PostMapping("author/{authorId}/books")
	public ResponseEntity<?> createBook(@Valid @PathVariable("authorId") Long id, @RequestBody Book book) {
		if (!userRepository.existsById(id)) {
			return new ResponseEntity<>("Author does not exist", HttpStatus.UNAUTHORIZED);
		}
		book.setUserid(id);
		Book createdBook = bookRepository.save(book);
		return new ResponseEntity<>(createdBook, HttpStatus.CREATED);

	}

	@PutMapping("author/{authorId}/books/{bookId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> updateBook(@PathVariable("authorId") Long authorId, @PathVariable("bookId") Long bookId,
			@RequestBody Book book) {

		if (!userRepository.existsById((long) authorId)) {
			return new ResponseEntity<>("Author does not exist", HttpStatus.UNAUTHORIZED);
		}
		book.setUserid(authorId);
		Boolean updateBook = bookService.updateBook(authorId, bookId, book);
		if (!updateBook) {
			return new ResponseEntity<>("Book does not exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updateBook, HttpStatus.CREATED);

	}

	@GetMapping("author/books/search")
	@ResponseBody
	public ResponseEntity<?> searchBook(@RequestParam(required = false) String category,
			@RequestParam(required = false) String author, @RequestParam(required = false) BigDecimal price,
			@RequestParam(required = false) String publisher) {
		Set<Book> bookList = bookService.searchBook(category, author, price, publisher);
		if (!bookList.isEmpty()) {
			return new ResponseEntity<>(bookList, HttpStatus.OK);
		}
		return ResponseEntity.ok().body("No Books Found");

	}

}
