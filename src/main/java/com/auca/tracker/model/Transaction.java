package com.auca.tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private double amount;
    private String rawLine;

    @ManyToOne
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getRawLine() { return rawLine; }
    public void setRawLine(String rawLine) { this.rawLine = rawLine; }
    public AccountHolder getAccountHolder() { return accountHolder; }
    public void setAccountHolder(AccountHolder accountHolder) { this.accountHolder = accountHolder; }
    public Merchant getMerchant() { return merchant; }
    public void setMerchant(Merchant merchant) { this.merchant = merchant; }
}
