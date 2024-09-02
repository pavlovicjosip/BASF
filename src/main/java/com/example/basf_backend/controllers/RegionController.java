package com.example.basf_backend.controllers;

import com.example.basf_backend.models.entities.RegionTypeEntity;

import com.example.basf_backend.repositories.RegionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionTypeRepository regionRepository;

    @Autowired
    public RegionController(RegionTypeRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @GetMapping
    public ResponseEntity<List<RegionTypeEntity>> getAllRegions() {
        return ResponseEntity.ok(regionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionTypeEntity> getRegionById(@PathVariable Long id) {
        return regionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegionTypeEntity> createRegion(@RequestBody RegionTypeEntity region) {
        return ResponseEntity.ok(regionRepository.save(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionTypeEntity> updateRegion(@PathVariable Long id, @RequestBody RegionTypeEntity region) {
        if (!regionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        region.setId(id);
        return ResponseEntity.ok(regionRepository.save(region));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        if (!regionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        regionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}