package com.auca.tracker.repository;

import com.auca.tracker.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, UUID> {
    // B1: DERIVED (IgnoreCase, OrderBy)
    List<AccountHolder> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    // C4: JPQL (DISTINCT, path navigation)
    @Query("SELECT DISTINCT t.accountHolder FROM Transaction t WHERE t.merchant.id = :merchantId")
    List<AccountHolder> findByMerchantId(@Param("merchantId") UUID merchantId);
}
