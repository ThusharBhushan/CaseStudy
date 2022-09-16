package com.bookservice.repository;

import java.math.BigDecimal;
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
	Set<Book> searchBookByPrice(@Param("price") BigDecimal price);

	@Query(value = "select * from book b where  b.publisher=:publisher", nativeQuery = true)
	Set<Book> searchBookByPublisher(@Param("publisher") String publisher);

	@Query(value = "select * from book b where b.userid=:id", nativeQuery = true)
	Iterable<Book> findBooksByUserId(@Param("id") Long id);

	@Query(value = "select b.* from `casestudy`.`payment` as  p join `casestudy`.`book`as b on p.bookid=b.id"
			+ " where b.id=:bookId and p.paymentid=:paymentId", nativeQuery = true)
	Book findBookForReader(@Param("bookId") Long bookId, @Param("paymentId") String paymentId);

}
