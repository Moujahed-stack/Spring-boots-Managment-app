package com.example.demo.Service;

import com.example.demo.model.Sales;
import com.example.demo.model.SalesTransaction;
import com.example.demo.repository.SalesRepository;
import com.example.demo.repository.SalesTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SalesTransactionRepository salesTransactionRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Optional<Sales> getSalesById(Long id) {
        return salesRepository.findById(id);
    }

    @Transactional
    public Sales createSales(Sales sales) {
        sales.setCreationDate(new Date());
        sales.setTotal(calculateTotal(sales.getTransactions()));
        sales = salesRepository.save(sales);
        for (SalesTransaction transaction : sales.getTransactions()) {
            transaction.setSales(sales);
            salesTransactionRepository.save(transaction);
        }
        return sales;
    }

    @Transactional
    public Sales updateSales(Long id, Sales salesDetails) {
        Sales sales = salesRepository.findById(id).orElseThrow(() -> new RuntimeException("Sales not found"));
        sales.setClient(salesDetails.getClient());
        sales.setSeller(salesDetails.getSeller());
        sales.setTotal(calculateTotal(salesDetails.getTransactions()));

        for (SalesTransaction transaction : salesDetails.getTransactions()) {
            SalesTransaction existingTransaction = salesTransactionRepository.findById(transaction.getId())
                    .orElseThrow(() -> new RuntimeException("Transaction not found"));
            existingTransaction.setProduct(transaction.getProduct());
            existingTransaction.setQuantity(transaction.getQuantity());
            existingTransaction.setPrice(transaction.getPrice());
            salesTransactionRepository.save(existingTransaction);
        }

        return salesRepository.save(sales);
    }

    private Double calculateTotal(List<SalesTransaction> transactions) {
        return transactions.stream()
                .mapToDouble(t -> t.getPrice() * t.getQuantity())
                .sum();
    }
}
