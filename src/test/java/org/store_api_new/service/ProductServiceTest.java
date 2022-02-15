package org.store_api_new.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.store_api_new.exception.NotFoundEntityException;
import org.store_api_new.model.Product;
import org.store_api_new.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyLong;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product createProduct(String title, Long available, BigDecimal price) {
        Product product = new Product();
        product.setTitle(title);
        product.setAvailable(available);
        product.setPrice(price);
        return product;
    }

    @Test
    void getByIdReturnEntity() {
        Product product = new Product();
        product.setTitle("AirPods Pro");
        product.setAvailable(10L);
        product.setPrice(BigDecimal.valueOf(249.00));
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        Product returnedProduct = productService.getById(anyLong());

        verify(productRepository, times(1)).findById(anyLong());
        assertThat(returnedProduct).isEqualTo(product);
    }

    @Test
    void getByIdThrowEx() {
        assertThrows(NotFoundEntityException.class,() -> productService.getById(anyLong()));
    }
}