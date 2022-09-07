package com.bookservice.service;

import java.math.BigDecimal;
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
			Book updatedBook =bookToUpdate.get();
			updatedBook.setTitle(book.getTitle());
			updatedBook.setPublisher(book.getPublisher());
			updatedBook.setPublished_date(book.getPublished_date());
			updatedBook.setPrice(book.getPrice());
			updatedBook.setContent(book.getContent());
			updatedBook.setCategory(book.getCategory());
			updatedBook.setAuthor(book.getAuthor());
			return bookRepository.save(updatedBook);
		}

		return null;

	}

	public Set<Book> searchBook(String category, String author, BigDecimal price, String publisher) {
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
