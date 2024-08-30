package com.example.basf_backend.models;

import com.example.basf_backend.models.enums.RegionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(unique = true, nullable = false)
    private int productID;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productType;

    @ElementCollection
    private List<RegionType> regions;


}