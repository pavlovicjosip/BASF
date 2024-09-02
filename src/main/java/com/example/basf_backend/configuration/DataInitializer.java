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
                        new Product(100305, "Potassium Hydroxide", "Solid", Arrays.asList(RegionType.EUROPE, RegionType.NORTH_AMERICA)),
                        new Product(100306, "Methanol", "Liquid", Arrays.asList(RegionType.ASIA, RegionType.SOUTH_AMERICA)),
                        new Product(100307, "Ammonia", "Gas", Arrays.asList(RegionType.AFRICA, RegionType.AUSTRALIA)),
                        new Product(100308, "Phosphoric Acid", "Liquid", Arrays.asList(RegionType.NORTH_AMERICA, RegionType.EUROPE)),
                        new Product(100309, "Titanium Dioxide", "Solid", Arrays.asList(RegionType.ASIA, RegionType.AFRICA)),
                        new Product(100310, "Hydrogen Peroxide", "Liquid", Arrays.asList(RegionType.SOUTH_AMERICA, RegionType.EUROPE)),
                        new Product(100311, "Sodium Hydroxide", "Solid", Arrays.asList(RegionType.NORTH_AMERICA, RegionType.ASIA)),
                        new Product(100312, "Acetic Acid", "Liquid", Arrays.asList(RegionType.EUROPE, RegionType.AUSTRALIA)),
                        new Product(100313, "Carbon Dioxide", "Gas", Arrays.asList(RegionType.AFRICA, RegionType.SOUTH_AMERICA)),
                        new Product(100314, "Magnesium Sulfate", "Solid", Arrays.asList(RegionType.ASIA, RegionType.NORTH_AMERICA)),
                        new Product(100315, "Nitric Acid", "Liquid", Arrays.asList(RegionType.EUROPE, RegionType.AFRICA)),
                        new Product(100316, "Chlorine", "Gas", Arrays.asList(RegionType.SOUTH_AMERICA, RegionType.AUSTRALIA)),
                        new Product(100317, "Aluminum Oxide", "Solid", Arrays.asList(RegionType.NORTH_AMERICA, RegionType.ASIA)),
                        new Product(100318, "Glycerol", "Liquid", Arrays.asList(RegionType.EUROPE, RegionType.SOUTH_AMERICA)),
                        new Product(100319, "Nitrogen", "Gas", Arrays.asList(RegionType.AFRICA, RegionType.NORTH_AMERICA)),
                        new Product(100320, "Copper Sulfate", "Solid", Arrays.asList(RegionType.ASIA, RegionType.AUSTRALIA)),
                        new Product(100321, "Formaldehyde", "Liquid", Arrays.asList(RegionType.EUROPE, RegionType.AFRICA)),
                        new Product(100322, "Helium", "Gas", Arrays.asList(RegionType.NORTH_AMERICA, RegionType.SOUTH_AMERICA)),
                        new Product(100323, "Zinc Oxide", "Solid", Arrays.asList(RegionType.ASIA, RegionType.EUROPE)),
                        new Product(100324, "Isopropyl Alcohol", "Liquid", Arrays.asList(RegionType.AUSTRALIA, RegionType.NORTH_AMERICA))
                );
                productRepository.saveAll(products);
            }
        };
    }
}