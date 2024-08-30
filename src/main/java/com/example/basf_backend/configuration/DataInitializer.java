package com.example.basf_backend.configuration;

import com.example.basf_backend.models.Product;
import com.example.basf_backend.models.entities.ProductTypeEntity;
import com.example.basf_backend.models.entities.RegionTypeEntity;
import com.example.basf_backend.models.enums.ProductType;
import com.example.basf_backend.models.enums.RegionType;
import com.example.basf_backend.repositories.ProductRepository;
import com.example.basf_backend.repositories.ProductTypeRepository;
import com.example.basf_backend.repositories.RegionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private RegionTypeRepository regionTypeRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {

            for (ProductType type : ProductType.values()) {
                if (!productTypeRepository.existsByName(type)) {
                    ProductTypeEntity productTypeEntity = new ProductTypeEntity();
                    productTypeEntity.setName(type);
                    productTypeRepository.save(productTypeEntity);
                }
            }

            // Initialize Region table
            for (RegionType region : RegionType.values()) {
                if (!regionTypeRepository.existsByName(region)) {
                    RegionTypeEntity regionEntity = new RegionTypeEntity();
                    regionEntity.setName(region);
                    regionTypeRepository.save(regionEntity);
                }
            }


            // Initialize products if they don't exist
            if (productRepository.count() == 0) {
                List<Product> products = Arrays.asList(
                        new Product(100300, "Sodium Chloride", "Solid", Arrays.asList(RegionType.EUROPE, RegionType.ASIA)),
                        new Product(100301, "Calcium Carbonate", "Solid", Arrays.asList(RegionType.ASIA, RegionType.AUSTRALIA)),
                        new Product(100302, "Hydrochloric Acid", "Liquid", Arrays.asList(RegionType.NORTH_AMERICA, RegionType.SOUTH_AMERICA)),
                        new Product(100303, "Ethanol", "Liquid", Arrays.asList(RegionType.EUROPE, RegionType.NORTH_AMERICA)),
                        new Product(100304, "Sulfur Dioxide", "Gas", Arrays.asList(RegionType.AFRICA, RegionType.ASIA))
                );
                productRepository.saveAll(products);
            }
        };
    }
}