package com.auca.tracker.repository;

import com.auca.tracker.model.SpendingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpendingCategoryRepository extends JpaRepository<SpendingCategory, UUID> {
    // C3: JPQL (IS EMPTY on the collection)
    @Query("SELECT c FROM SpendingCategory c WHERE c.merchants IS EMPTY")
    List<SpendingCategory> findUnused();
}
