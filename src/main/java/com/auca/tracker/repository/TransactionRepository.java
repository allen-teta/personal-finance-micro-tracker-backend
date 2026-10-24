package com.auca.tracker.repository;

import com.auca.tracker.model.Transaction;
import com.auca.tracker.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    // B2: DERIVED (enum parameter, OrderBy)
    List<Transaction> findByStatusOrderByTransactionDateAsc(TransactionStatus status);

    // B3: DERIVED (Between, inclusive)
    List<Transaction> findByTransactionDateBetweenOrderByTransactionDateAsc(LocalDate start, LocalDate end);

    // D1: DERIVED (existsBy, And, Not)
    boolean existsByMerchantIdAndTransactionDateAndStatusNot(UUID merchantId, LocalDate transactionDate, TransactionStatus status);
}
