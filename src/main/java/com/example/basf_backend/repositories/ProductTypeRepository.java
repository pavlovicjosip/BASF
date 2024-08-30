package com.example.basf_backend.repositories;

import com.example.basf_backend.models.entities.ProductTypeEntity;
import com.example.basf_backend.models.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {
    boolean existsByName(ProductType name);
}
