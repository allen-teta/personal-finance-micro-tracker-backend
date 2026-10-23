package com.auca.tracker.repository;

import com.auca.tracker.model.MerchantCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantCodeRepository extends JpaRepository<MerchantCode, UUID> {
}
