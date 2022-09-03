package com.bookservice.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.entity.Book;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.UserRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;

	public Book updateBook(Long authorId, Long bookId, Book book) {
		Optional<Book> bookToUpdate = bookRepository.findById((long) bookId);
		if (bookToUpdate.isPresent()) {
			return bookRepository.save(book);
		}

		return null;

	}

	public Set<Book> searchBook(String category, String author, String price, String publisher) {
		Set<Book> bookList = new HashSet<>();;
		if (category != null) {
			bookList.addAll(bookRepository.searchBookByCategory(category));
		}
		if (author != null) {
			bookList.addAll(bookRepository.searchBookByAuthor(author));

		}
		if (price != null) {
			bookList.addAll(bookRepository.searchBookByPrice(price));
		}
		if (publisher != null) {
			bookList.addAll(bookRepository.searchBookByPublisher(publisher));

		}
		return bookList;
	}

}
