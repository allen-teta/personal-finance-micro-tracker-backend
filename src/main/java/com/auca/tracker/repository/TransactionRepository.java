package com.auca.tracker.repository;

import com.auca.tracker.model.Transaction;
import com.auca.tracker.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    // F1: JPQL (GROUP BY, COUNT)
    @Query("SELECT t.status, COUNT(t) FROM Transaction t GROUP BY t.status ORDER BY t.status")
    List<Object[]> statsByStatus();

    // F2: JPQL (UPDATE, one query, no Java loop)
    @Modifying
    @Query("UPDATE Transaction t SET t.status = :ignored WHERE t.merchant.id = :merchantId AND t.transactionDate = :date AND t.status <> :reviewed")
    int cancelDay(@Param("merchantId") UUID merchantId, @Param("date") LocalDate date,
                  @Param("ignored") TransactionStatus ignored, @Param("reviewed") TransactionStatus reviewed);

    // Bonus dashboard: DERIVED countBy
    long countByStatus(TransactionStatus status);

    long countByTransactionDate(LocalDate transactionDate);
}
