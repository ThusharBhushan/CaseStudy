package com.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookservice.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	@Query(value = "select * from payment p where p.paymentid=:paymentId", nativeQuery = true)
	Payment getPurchasedBook(@Param("paymentId") String paymentId);
}
