package com.example.basf_backend.repositories;

import com.example.basf_backend.models.entities.RegionTypeEntity;
import com.example.basf_backend.models.enums.RegionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionTypeRepository extends JpaRepository<RegionTypeEntity,Long> {
    boolean existsByName(RegionType name);
}
