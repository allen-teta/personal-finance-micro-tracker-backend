package com.auca.tracker.service;

import com.auca.tracker.model.MerchantCode;
import com.auca.tracker.repository.MerchantCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantCodeService {
    private final MerchantCodeRepository repo;

    public MerchantCodeService(MerchantCodeRepository repo) {
        this.repo = repo;
    }

    public String save(MerchantCode x) {
        x.setId(null);
        repo.save(x);
        return "Merchant code saved successfully";
    }

    public List<MerchantCode> getAll() {
        return repo.findAll();
    }

    public Optional<MerchantCode> getById(UUID id) {
        return repo.findById(id);
    }

    public String update(UUID id, MerchantCode x) {
        Optional<MerchantCode> found = repo.findById(id);
        if (found.isEmpty()) {
            return "The merchant code with that id does not exist";
        }
        MerchantCode existing = found.get();
        existing.setCodeNumber(x.getCodeNumber());
        existing.setName(x.getName());
        repo.save(existing);
        return "Merchant code updated successfully";
    }

    public String delete(UUID id) {
        if (!repo.existsById(id)) {
            return "The merchant code with that id does not exist";
        }
        repo.deleteById(id);
        return "Merchant code deleted successfully";
    }

    // C2
    public List<MerchantCode> getFree() {
        return repo.findFree();
    }
}
