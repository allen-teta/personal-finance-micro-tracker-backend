package com.auca.tracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String keyword;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "merchant_code_id")
    private MerchantCode merchantCode;

    @ManyToMany
    @JoinTable(
            name = "merchant_spending_category",
            joinColumns = @JoinColumn(name = "merchant_id"),
            inverseJoinColumns = @JoinColumn(name = "spending_category_id"))
    private List<SpendingCategory> spendingCategories = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public MerchantCode getMerchantCode() { return merchantCode; }
    public void setMerchantCode(MerchantCode merchantCode) { this.merchantCode = merchantCode; }
    public List<SpendingCategory> getSpendingCategories() { return spendingCategories; }
    public void setSpendingCategories(List<SpendingCategory> spendingCategories) { this.spendingCategories = spendingCategories; }
}
