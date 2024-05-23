package com.example.demo.Controller;

import com.example.demo.model.products;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<products> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public products createProduct(@RequestBody products product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<products> updateProduct(@PathVariable Long id, @RequestBody products productDetails) {
        return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    }
}
