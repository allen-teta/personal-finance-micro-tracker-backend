package com.auca.tracker.service;

import com.auca.tracker.model.Merchant;
import com.auca.tracker.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantService {
    private final MerchantRepository repo;

    public MerchantService(MerchantRepository repo) {
        this.repo = repo;
    }

    public String save(Merchant x) {
        x.setId(null);
        repo.save(x);
        return "Merchant saved successfully";
    }

    public List<Merchant> getAll() {
        return repo.findAll();
    }

    public Optional<Merchant> getById(UUID id) {
        return repo.findById(id);
    }

    public String update(UUID id, Merchant x) {
        Optional<Merchant> found = repo.findById(id);
        if (found.isEmpty()) {
            return "The merchant with that id does not exist";
        }
        Merchant existing = found.get();
        existing.setName(x.getName());
        existing.setKeyword(x.getKeyword());
        existing.setPhone(x.getPhone());
        existing.setMerchantCode(x.getMerchantCode());
        repo.save(existing);
        return "Merchant updated successfully";
    }

    public String delete(UUID id) {
        if (!repo.existsById(id)) {
            return "The merchant with that id does not exist";
        }
        repo.deleteById(id);
        return "Merchant deleted successfully";
    }

    // C1
    public List<Merchant> getBySpendingCategory(String name) {
        return repo.findBySpendingCategoryName(name);
    }
}
