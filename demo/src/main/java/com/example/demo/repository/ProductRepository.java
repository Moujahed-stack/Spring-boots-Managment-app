package com.example.demo.repository;

import com.example.demo.model.products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<products, Long> {
}
