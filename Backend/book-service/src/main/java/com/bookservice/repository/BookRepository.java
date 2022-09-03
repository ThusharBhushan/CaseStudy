package com.bookservice.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookservice.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = "select * from book b where b.category=:category", nativeQuery = true)
	Set<Book> searchBookByCategory(@Param("category") String category);

	@Query(value = "select * from book b where b.author=:author", nativeQuery = true)
	Set<Book> searchBookByAuthor(@Param("author") String author);

	@Query(value = "select * from book b where b.price=:price", nativeQuery = true)
	Set<Book> searchBookByPrice(@Param("price") String price);

	@Query(value = "select * from book b where  b.publisher=:publisher", nativeQuery = true)
	Set<Book> searchBookByPublisher(@Param("publisher") String publisher);

}
