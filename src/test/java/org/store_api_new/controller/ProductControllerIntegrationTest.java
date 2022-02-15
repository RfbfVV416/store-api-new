package org.store_api_new.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.store_api_new.model.Product;
import org.store_api_new.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc productController;

    @Autowired
    private ProductRepository productRepository;

    private Product saveProduct(String title, Long available, BigDecimal price) {
        Product product = new Product();
        product.setTitle(title);
        product.setAvailable(available);
        product.setPrice(price);
        return productRepository.save(product);
    }

    @Test
    void getAll() throws Exception {
        Product product1 = saveProduct("AirPods Pro", 10L, BigDecimal.valueOf(249.00));
        Product product2 = saveProduct("Video Conference Lighting Kit", 15L, BigDecimal.valueOf(19.00));

        MvcResult response =
                productController.perform(get("/products"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<Product> returnedProducts =
                MockMvcUtil.jsonToTypeList(response.getResponse().getContentAsString(), Product.class);

        assertNotNull(returnedProducts);
        assertEquals(2, returnedProducts.size());
        assertThat(returnedProducts, containsInAnyOrder(product1, product2));
    }
}