package com.example.demo.Controller;

import com.example.demo.model.Sales;
import com.example.demo.Service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSalesById(@PathVariable Long id) {
        return salesService.getSalesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sales createSales(@RequestBody Sales sales) {
        return salesService.createSales(sales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sales> updateSales(@PathVariable Long id, @RequestBody Sales salesDetails) {
        return ResponseEntity.ok(salesService.updateSales(id, salesDetails));
    }
}
