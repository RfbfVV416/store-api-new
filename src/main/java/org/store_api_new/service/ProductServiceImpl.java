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
    public Product getById(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundEntityException("There is no product with id " + productId));
    }

    @Override
    public void checkAvailability(Long quantity, Long available){
        if (quantity > available){
            throw new ProductOutOfStockException("Not enough products");
        }
    }
}
