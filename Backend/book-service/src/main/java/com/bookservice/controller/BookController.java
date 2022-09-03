package com.bookservice.controller;

import java.util.List;
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
import com.bookservice.exception.BookNotFoundException;
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

	@PostMapping("author/{authorId}/books")
	public ResponseEntity createBook(@Valid @PathVariable("authorId") Long id, @RequestBody Book book) {
		if (!userRepository.existsById(id)) {
			return new ResponseEntity<>("Author does not exist", HttpStatus.UNAUTHORIZED);
		}
		book.setUserid(id);
		Book createdBook = bookRepository.save(book);
		return new ResponseEntity<>(createdBook, HttpStatus.CREATED);

	}

	@PutMapping("author/{authorId}/books")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity updateBook(@PathVariable("authorId") Long authorId, @PathVariable("authorId") Long bookId,
			@RequestBody Book book) throws BookNotFoundException {

		if (!bookRepository.existsById((long) authorId)) {
			return new ResponseEntity<>("Author does not exist", HttpStatus.UNAUTHORIZED);
		}
		Book updateBook = bookService.updateBook(authorId, bookId, book);
		if (updateBook == null) {
			return new ResponseEntity<>("Book does not exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updateBook, HttpStatus.FOUND);

	}

	@GetMapping("/books/search")
	@ResponseBody
	public ResponseEntity searchBook(@RequestParam(required = false) String category,
			@RequestParam(required = false) String author, @RequestParam(required = false) String price,
			@RequestParam(required = false) String publisher) {
		Set<Book> bookList=bookService.searchBook(category, author, price, publisher);
		if(bookList!=null) {
			return new ResponseEntity<>(bookList, HttpStatus.FOUND);
			
		}
		return new ResponseEntity<>("No Books Found", HttpStatus.NOT_FOUND);

	}

}
