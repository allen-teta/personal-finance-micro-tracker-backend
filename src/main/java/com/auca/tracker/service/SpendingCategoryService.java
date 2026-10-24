package com.auca.tracker.service;

import com.auca.tracker.model.SpendingCategory;
import com.auca.tracker.repository.SpendingCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpendingCategoryService {
    private final SpendingCategoryRepository repo;

    public SpendingCategoryService(SpendingCategoryRepository repo) {
        this.repo = repo;
    }

    public String save(SpendingCategory x) {
        x.setId(null);
        repo.save(x);
        return "Spending category saved successfully";
    }

    public List<SpendingCategory> getAll() {
        return repo.findAll();
    }

    public Optional<SpendingCategory> getById(UUID id) {
        return repo.findById(id);
    }

    public String update(UUID id, SpendingCategory x) {
        Optional<SpendingCategory> found = repo.findById(id);
        if (found.isEmpty()) {
            return "The spending category with that id does not exist";
        }
        SpendingCategory existing = found.get();
        existing.setName(x.getName());
        repo.save(existing);
        return "Spending category updated successfully";
    }

    public String delete(UUID id) {
        if (!repo.existsById(id)) {
            return "The spending category with that id does not exist";
        }
        repo.deleteById(id);
        return "Spending category deleted successfully";
    }

    // C3
    public List<SpendingCategory> getUnused() {
        return repo.findUnused();
    }
}
