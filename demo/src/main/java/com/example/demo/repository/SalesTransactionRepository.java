package com.example.demo.repository;

import com.example.demo.model.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesTransactionRepository extends JpaRepository<SalesTransaction, Long> {
}
