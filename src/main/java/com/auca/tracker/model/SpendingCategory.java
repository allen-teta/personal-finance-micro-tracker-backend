package com.auca.tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "spending_category")
public class SpendingCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "spendingCategories")
    private List<Merchant> merchants = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Merchant> getMerchants() { return merchants; }
    public void setMerchants(List<Merchant> merchants) { this.merchants = merchants; }
}
