package com.auca.tracker.repository;

import com.auca.tracker.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, UUID> {
}
