package com.example.demo.Service;

import com.example.demo.model.products;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<products> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<products> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public products createProduct(products product) {
        product.setCreationDate(new Date());
        return productRepository.save(product);
    }

    public products updateProduct(Long id, products productDetails) {
        products product = productRepository.findById(id).orElseThrow();
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setCategory(productDetails.getCategory());
        return productRepository.save(product);
    }
}
