package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date creationDate;
    private String client;
    private String seller;
    private Double total;

    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL)
    private List<SalesTransaction> transactions;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<SalesTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<SalesTransaction> transactions) {
        this.transactions = transactions;
    }
}
