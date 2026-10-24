package com.auca.tracker.service;

import com.auca.tracker.model.Transaction;
import com.auca.tracker.model.TransactionStatus;
import com.auca.tracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    // D1: safe saving
    public String save(Transaction x) {
        if (x.getTransactionDate() == null) {
            return "Transaction date is required";
        }
        if (x.getTransactionDate().isBefore(LocalDate.now())) {
            return "Transaction date cannot be in the past";
        }
        if (x.getMerchant() != null && x.getMerchant().getId() != null
                && repo.existsByMerchantIdAndTransactionDateAndStatusNot(
                        x.getMerchant().getId(), x.getTransactionDate(), TransactionStatus.IGNORED)) {
            return "Merchant already has a transaction on that date";
        }
        x.setId(null);
        x.setStatus(TransactionStatus.IMPORTED);
        repo.save(x);
        return "Transaction saved successfully";
    }

    public List<Transaction> getAll() {
        return repo.findAll();
    }

    public Optional<Transaction> getById(UUID id) {
        return repo.findById(id);
    }

    // general update: never touches status
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

    // B2
    public List<Transaction> getByStatus(TransactionStatus status) {
        return repo.findByStatusOrderByTransactionDateAsc(status);
    }

    // B3
    public List<Transaction> getBetween(LocalDate start, LocalDate end) {
        return repo.findByTransactionDateBetweenOrderByTransactionDateAsc(start, end);
    }

    // D2: status flow
    public String changeStatus(UUID id, TransactionStatus newStatus) {
        Optional<Transaction> found = repo.findById(id);
        if (found.isEmpty()) {
            return "The transaction with that id does not exist";
        }
        Transaction t = found.get();
        TransactionStatus current = t.getStatus();
        boolean allowed = false;
        if (current == TransactionStatus.IMPORTED) {
            allowed = newStatus == TransactionStatus.CATEGORIZED || newStatus == TransactionStatus.IGNORED;
        } else if (current == TransactionStatus.CATEGORIZED) {
            allowed = newStatus == TransactionStatus.REVIEWED || newStatus == TransactionStatus.IGNORED;
        }
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        t.setStatus(newStatus);
        repo.save(t);
        return "Transaction status updated successfully";
    }
}
