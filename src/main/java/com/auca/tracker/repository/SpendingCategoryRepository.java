package com.auca.tracker.repository;

import com.auca.tracker.model.SpendingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpendingCategoryRepository extends JpaRepository<SpendingCategory, UUID> {
}
