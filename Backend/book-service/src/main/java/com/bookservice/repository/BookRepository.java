package com.bookservice.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookservice.entity.Book;
import com.bookservice.enums.Category;
import com.bookservice.enums.Status;

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
	
	@Modifying
	@Query(value = "update book b set b.title=:title,b.category=:category,b.price=:price,b.author=:author,b.published_date=:publishedDate,"
			+"b.active=:active,b.content=:content,b.publisher=:publisher where b.id=:id and b.userid=:userid",nativeQuery=true)
	void bookToUpdate(@Param("userid") Long userid,@Param("id") Long id,
			@Param("title") String title,@Param("category") String category,@Param("price") BigDecimal price,@Param("author") String author,
			@Param("publishedDate") Date publishedDate,@Param("active") String active,@Param("content") String content,@Param("publisher") String publisher);

}
