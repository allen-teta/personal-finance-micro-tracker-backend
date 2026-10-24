package com.auca.tracker.repository;

import com.auca.tracker.model.MerchantCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MerchantCodeRepository extends JpaRepository<MerchantCode, UUID> {
    // C2: JPQL (NOT EXISTS subquery)
    @Query("SELECT l FROM MerchantCode l WHERE NOT EXISTS (SELECT p FROM Merchant p WHERE p.merchantCode = l) ORDER BY l.codeNumber")
    List<MerchantCode> findFree();
}
