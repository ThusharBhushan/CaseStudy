package com.bookservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.entity.Book;
import com.bookservice.entity.Payment;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.PaymentRepository;
import com.bookservice.repository.UserRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	UserRepository userRepository;

	@Transactional
	public Boolean updateBook(Long authorId, Long bookId, Book book) {
		Optional<Book> bookToUpdate = bookRepository.findById((long) bookId);
		if (bookToUpdate.isPresent()) {
			bookRepository.bookToUpdate(authorId, bookId, book.getTitle(), book.getCategory().toString(), book.getPrice(),
					book.getAuthor(), book.getPublished_date(), book.getActive().toString(), book.getContent(),
					book.getPublisher());
			return true;
		}
		return false;

	}

	public Set<Book> searchBook(String category, String author, BigDecimal price, String publisher) {
		Set<Book> bookList = new HashSet<>();
		;
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

	public Iterable<Book> getAllBooks(Long id) {
		return bookRepository.findBooksByUserId(id);
	}

	public Iterable<Book> getAllBooksForReader() {
		return bookRepository.findAll();
	}

	public Payment doPayment(Long bookId, String userName, String mailId) {
		UUID uuid = UUID.randomUUID();
		Payment payment = new Payment();
		payment.setBookid(bookId);
		payment.setMailid(mailId);
		payment.setUsername(userName);
		payment.setPaymentid(uuid.toString());
		payment.setPayment_date(LocalDateTime.now());
		return paymentRepository.save(payment);
	}

	public Payment getPurchasedBooksForReader(String paymentId) {
		return paymentRepository.getPurchasedBook(paymentId);
	}

	public Book getReaderBooks(Long bookId, String paymentId) {
		return bookRepository.findBookForReader(bookId, paymentId);
	}

}
