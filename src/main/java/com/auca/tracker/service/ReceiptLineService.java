package com.auca.tracker.service;

import com.auca.tracker.model.ReceiptLine;
import com.auca.tracker.model.Transaction;
import com.auca.tracker.model.TransactionStatus;
import com.auca.tracker.repository.ReceiptLineRepository;
import com.auca.tracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReceiptLineService {
    private final ReceiptLineRepository repo;
    private final TransactionRepository transactionRepo;

    public ReceiptLineService(ReceiptLineRepository repo, TransactionRepository transactionRepo) {
        this.repo = repo;
        this.transactionRepo = transactionRepo;
    }

    // E1
    public String save(UUID transactionId, ReceiptLine x) {
        Optional<Transaction> found = transactionRepo.findById(transactionId);
        if (found.isEmpty()) {
            return "The transaction with that id does not exist";
        }
        Transaction t = found.get();
        if (t.getStatus() != TransactionStatus.REVIEWED) {
            return "Receipt lines are only for REVIEWED transactions";
        }
        x.setId(null);
        x.setRecordedDate(LocalDate.now());
        x.setTransaction(t);
        repo.save(x);
        return "Receipt line saved successfully";
    }

    public List<ReceiptLine> getAll() {
        return repo.findAll();
    }

    public Optional<ReceiptLine> getById(UUID id) {
        return repo.findById(id);
    }

    // E2
    public List<ReceiptLine> getByAccountNumber(String accountNumber) {
        return repo.findByAccountNumber(accountNumber);
    }

    // E3
    public String delete(UUID id) {
        if (!repo.existsById(id)) {
            return "The receipt line with that id does not exist";
        }
        repo.deleteById(id);
        return "Receipt line deleted successfully";
    }
}
