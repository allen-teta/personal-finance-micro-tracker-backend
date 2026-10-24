package com.auca.tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "receipt_line")
public class ReceiptLine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String itemName;
    private String comment;
    private int quantity;
    private LocalDate recordedDate;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDate getRecordedDate() { return recordedDate; }
    public void setRecordedDate(LocalDate recordedDate) { this.recordedDate = recordedDate; }
    public Transaction getTransaction() { return transaction; }
    public void setTransaction(Transaction transaction) { this.transaction = transaction; }
}
