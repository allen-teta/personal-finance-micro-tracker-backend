package com.auca.tracker.service;

import com.auca.tracker.model.TransactionStatus;
import com.auca.tracker.repository.AccountHolderRepository;
import com.auca.tracker.repository.MerchantCodeRepository;
import com.auca.tracker.repository.MerchantRepository;
import com.auca.tracker.repository.ReceiptLineRepository;
import com.auca.tracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {
    private final AccountHolderRepository accountHolderRepo;
    private final MerchantRepository merchantRepo;
    private final MerchantCodeRepository merchantCodeRepo;
    private final TransactionRepository transactionRepo;
    private final ReceiptLineRepository receiptLineRepo;

    public DashboardService(AccountHolderRepository accountHolderRepo, MerchantRepository merchantRepo,
                            MerchantCodeRepository merchantCodeRepo, TransactionRepository transactionRepo,
                            ReceiptLineRepository receiptLineRepo) {
        this.accountHolderRepo = accountHolderRepo;
        this.merchantRepo = merchantRepo;
        this.merchantCodeRepo = merchantCodeRepo;
        this.transactionRepo = transactionRepo;
        this.receiptLineRepo = receiptLineRepo;
    }

    public Map<String, Long> summary() {
        Map<String, Long> m = new LinkedHashMap<>();
        m.put("accountHolders", accountHolderRepo.count());
        m.put("merchants", merchantRepo.count());
        m.put("freeMerchantCodes", (long) merchantCodeRepo.findFree().size());
        m.put("transactionsToday", transactionRepo.countByTransactionDate(LocalDate.now()));
        m.put("imported", transactionRepo.countByStatus(TransactionStatus.IMPORTED));
        m.put("receiptLines", receiptLineRepo.count());
        return m;
    }
}
