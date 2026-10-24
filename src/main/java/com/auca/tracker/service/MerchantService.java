package com.auca.tracker.service;

import com.auca.tracker.model.Merchant;
import com.auca.tracker.model.MerchantCode;
import com.auca.tracker.model.SpendingCategory;
import com.auca.tracker.repository.MerchantCodeRepository;
import com.auca.tracker.repository.MerchantRepository;
import com.auca.tracker.repository.SpendingCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantService {
    private final MerchantRepository repo;
    private final SpendingCategoryRepository categoryRepo;
    private final MerchantCodeRepository codeRepo;

    public MerchantService(MerchantRepository repo, SpendingCategoryRepository categoryRepo, MerchantCodeRepository codeRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
        this.codeRepo = codeRepo;
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

    // D3: many-to-many, the owner (Merchant) is saved
    @Transactional
    public String addSpendingCategory(UUID merchantId, UUID categoryId) {
        Optional<Merchant> m = repo.findById(merchantId);
        if (m.isEmpty()) {
            return "The merchant with that id does not exist";
        }
        Optional<SpendingCategory> c = categoryRepo.findById(categoryId);
        if (c.isEmpty()) {
            return "That spending category does not exist";
        }
        Merchant merchant = m.get();
        if (merchant.getSpendingCategories().contains(c.get())) {
            return "The merchant already has that spending category";
        }
        merchant.getSpendingCategories().add(c.get());
        repo.save(merchant);
        return "Spending category added to the merchant";
    }

    // D4: move a merchant to another merchant code
    public String changeMerchantCode(UUID merchantId, int codeNumber) {
        Optional<Merchant> m = repo.findById(merchantId);
        if (m.isEmpty()) {
            return "The merchant with that id does not exist";
        }
        Optional<MerchantCode> code = codeRepo.findByCodeNumber(codeNumber);
        if (code.isEmpty()) {
            return "The merchant code with that codeNumber does not exist";
        }
        if (repo.existsByMerchantCodeAndIdNot(code.get(), merchantId)) {
            return "That merchant code is taken by another merchant";
        }
        Merchant merchant = m.get();
        merchant.setMerchantCode(code.get());
        repo.save(merchant);
        return "Merchant code changed successfully";
    }
}
