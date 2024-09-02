package com.example.basf_backend.controllers;

import com.example.basf_backend.models.entities.ProductTypeEntity;
import com.example.basf_backend.models.enums.ProductType;
import com.example.basf_backend.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
public class ProductTypeController {

    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeController(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductTypeEntity>> getAllProductTypes() {
        return ResponseEntity.ok(productTypeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeEntity> getProductTypeById(@PathVariable Long id) {
        return productTypeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductTypeEntity> createProductType(@RequestBody ProductTypeEntity productType) {
        return ResponseEntity.ok(productTypeRepository.save(productType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductTypeEntity> updateProductType(@PathVariable Long id, @RequestBody ProductTypeEntity productType) {
        if (!productTypeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productType.setId(id);
        return ResponseEntity.ok(productTypeRepository.save(productType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable Long id) {
        if (!productTypeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productTypeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}