package com.auca.tracker.service;

import com.auca.tracker.model.TransactionStatus;
import com.auca.tracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionReportService {
    private final TransactionRepository repo;

    public TransactionReportService(TransactionRepository repo) {
        this.repo = repo;
    }

    // F1
    public List<Object[]> statsByStatus() {
        return repo.statsByStatus();
    }

    // F2
    @Transactional
    public String cancelDay(UUID merchantId, LocalDate date) {
        int n = repo.cancelDay(merchantId, date, TransactionStatus.IGNORED, TransactionStatus.REVIEWED);
        return n + " transactions cancelled";
    }
}
