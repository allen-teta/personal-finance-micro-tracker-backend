package com.auca.tracker.service;

import com.auca.tracker.model.Transaction;
import com.auca.tracker.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionPageService {
    private final TransactionRepository repo;

    public TransactionPageService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Page<Transaction> getPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
