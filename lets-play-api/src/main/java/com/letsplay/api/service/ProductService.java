package com.letsplay.api.service;

import com.letsplay.api.entity.Product;
import com.letsplay.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET all
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET by id
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // POST create
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // PUT update
    public Optional<Product> updateProduct(String id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setUserId(updatedProduct.getUserId());
                    return productRepository.save(existingProduct);
                });
    }

    // DELETE
    public boolean deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}