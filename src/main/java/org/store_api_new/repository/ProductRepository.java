package org.store_api_new.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.store_api_new.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
