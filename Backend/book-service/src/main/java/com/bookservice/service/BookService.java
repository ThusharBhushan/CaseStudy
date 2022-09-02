package com.bookservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.entity.Book;
import com.bookservice.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Book updateBook(Integer authorId, Integer bookId, Book book) {
		if (bookRepository.existsById((long) authorId)) {
			Optional<Book> bookToUpdate = bookRepository.findById((long) bookId);
			if (bookToUpdate.isPresent()) {
				return bookRepository.save(book);
			}
		}
		return null;

	}

	public Book createBook(Integer id, Book book) {
		return bookRepository.save(book);

	}

}
