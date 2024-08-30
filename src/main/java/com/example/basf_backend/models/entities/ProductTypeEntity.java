package com.example.basf_backend.models.entities;

import com.example.basf_backend.models.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_type")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ProductType name;



}