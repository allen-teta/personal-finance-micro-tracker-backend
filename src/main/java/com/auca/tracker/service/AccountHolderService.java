package com.auca.tracker.service;

import com.auca.tracker.model.AccountHolder;
import com.auca.tracker.repository.AccountHolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountHolderService {
    private final AccountHolderRepository repo;

    public AccountHolderService(AccountHolderRepository repo) {
        this.repo = repo;
    }

    public String save(AccountHolder x) {
        x.setId(null);
        repo.save(x);
        return "Account holder saved successfully";
    }

    public List<AccountHolder> getAll() {
        return repo.findAll();
    }

    public Optional<AccountHolder> getById(UUID id) {
        return repo.findById(id);
    }

    public String update(UUID id, AccountHolder x) {
        Optional<AccountHolder> found = repo.findById(id);
        if (found.isEmpty()) {
            return "The account holder with that id does not exist";
        }
        AccountHolder existing = found.get();
        existing.setFirstName(x.getFirstName());
        existing.setLastName(x.getLastName());
        existing.setAccountNumber(x.getAccountNumber());
        existing.setPhone(x.getPhone());
        repo.save(existing);
        return "Account holder updated successfully";
    }

    public String delete(UUID id) {
        if (!repo.existsById(id)) {
            return "The account holder with that id does not exist";
        }
        repo.deleteById(id);
        return "Account holder deleted successfully";
    }

    // B1
    public List<AccountHolder> getByLastName(String lastName) {
        return repo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}
