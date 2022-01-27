package org.store_api_new.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.store_api_new.model.Product;
import org.store_api_new.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        productRepository.saveAll(createProducts());
    }

    private List<Product> createProducts(){
        return List.of(
                createProduct("AirPods Pro", 10L, BigDecimal.valueOf(249.00)),
                createProduct("Instax Mimi 11 camera", 28L, BigDecimal.valueOf(68.00)),
                createProduct("Video Conference Lighting Kit", 15L, BigDecimal.valueOf(19.00)),
                createProduct("Polaroid Originals OneStep", 8L, BigDecimal.valueOf(121.45))
        );
    }

    private Product createProduct(String title, Long available, BigDecimal price){
        Product product = new Product();
        product.setTitle(title);
        product.setAvailable(available);
        product.setPrice(price);
        return product;
    }
}
