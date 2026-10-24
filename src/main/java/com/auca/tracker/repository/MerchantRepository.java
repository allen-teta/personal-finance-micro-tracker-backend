package com.auca.tracker.repository;

import com.auca.tracker.model.Merchant;
import com.auca.tracker.model.MerchantCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    // C1: JPQL (JOIN on collection, LOWER)
    @Query("SELECT DISTINCT m FROM Merchant m JOIN m.spendingCategories c WHERE LOWER(c.name) = LOWER(:name)")
    List<Merchant> findBySpendingCategoryName(@Param("name") String name);

    // D4: DERIVED (existsBy, And, Not)
    boolean existsByMerchantCodeAndIdNot(MerchantCode merchantCode, UUID id);
}
