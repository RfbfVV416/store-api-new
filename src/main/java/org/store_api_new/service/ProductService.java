package org.store_api_new.service;

import org.store_api_new.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long productId);
    boolean checkAvailability(Long quantity, Long productId);
}
