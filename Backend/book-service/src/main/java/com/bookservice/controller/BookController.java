package com.bookservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.entity.Book;
import com.bookservice.exception.BookNotFoundException;
import com.bookservice.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/digitalbooks/")
public class BookController extends BaseController {

	@Autowired
	BookService bookService;

	@PostMapping("author/{authorId}/books")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Book createBook(@PathVariable("authorId") Integer id, @RequestBody Book book) {
		return bookService.createBook(id, book);

	}
	
	@PutMapping("author/{authorId}/books")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity updateBook(@PathVariable("authorId") Integer authorId,@PathVariable("authorId") Integer bookId, @RequestBody Book book)throws BookNotFoundException {
		Book updateBook= bookService.updateBook(authorId,bookId,book);
		if(updateBook==null) {
		 throw new BookNotFoundException("Book Not Found");
		}
		ResponseEntity reponseEntity = new ResponseEntity<>(updateBook,HttpStatus.FOUND);
		return reponseEntity;
		
	}
	
	@GetMapping("/api/v1/digitalbooks/books/search")
	public Book searchBook(@RequestParam(defaultValue = "empty") String category,@RequestParam(defaultValue = "empty") String author,
			@RequestParam(defaultValue = "empty") BigDecimal price,@RequestParam(defaultValue = "empty") String publisher) {
		return null;
		
	}

}
