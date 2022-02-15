package org.store_api_new.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.store_api_new.exception.NotFoundEntityException;
import org.store_api_new.exception.ProductOutOfStockException;
import org.store_api_new.model.Product;
import org.store_api_new.repository.ProductRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean checkAvailability(Long quantity, Long productId){
        return quantity <= getById(productId).getAvailable();
    }

    @Override
    public Product getById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundEntityException("There is no product with id " + productId));
    }
}
