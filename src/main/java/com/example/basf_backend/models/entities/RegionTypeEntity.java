package com.example.basf_backend.models.entities;

import com.example.basf_backend.models.enums.RegionType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "region_type")
public class RegionTypeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RegionType name;


}