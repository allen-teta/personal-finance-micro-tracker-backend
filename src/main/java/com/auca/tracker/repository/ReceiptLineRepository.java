package com.auca.tracker.repository;

import com.auca.tracker.model.ReceiptLine;
import com.auca.tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReceiptLineRepository extends JpaRepository<ReceiptLine, UUID> {
    // E2: JPQL (path navigation, ORDER BY)
    @Query("SELECT r FROM ReceiptLine r WHERE r.transaction.accountHolder.accountNumber = :accountNumber ORDER BY r.recordedDate DESC")
    List<ReceiptLine> findByAccountNumber(@Param("accountNumber") String accountNumber);

    // E3: DERIVED
    boolean existsByTransaction(Transaction transaction);
}
