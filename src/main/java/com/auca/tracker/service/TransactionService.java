package com.auca.tracker.service;

import com.auca.tracker.model.Transaction;
import com.auca.tracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public String save(Transaction x) {
        x.setId(null);
        repo.save(x);
        return "Transaction saved successfully";
    }

    public List<Transaction> getAll() {
        return repo.findAll();
    }

    public Optional<Transaction> getById(UUID id) {
        return repo.findById(id);
    }

    public String update(UUID id, Transaction x) {
        Optional<Transaction> found = repo.findById(id);
        if (found.isEmpty()) {
            return "The transaction with that id does not exist";
        }
        Transaction existing = found.get();
        existing.setTransactionDate(x.getTransactionDate());
        existing.setAmount(x.getAmount());
        existing.setRawLine(x.getRawLine());
        existing.setAccountHolder(x.getAccountHolder());
        existing.setMerchant(x.getMerchant());
        repo.save(existing);
        return "Transaction updated successfully";
    }

    public String delete(UUID id) {
        if (!repo.existsById(id)) {
            return "The transaction with that id does not exist";
        }
        repo.deleteById(id);
        return "Transaction deleted successfully";
    }
}
