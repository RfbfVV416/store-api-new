package org.store_api_new.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    @Min(0)
    private Long available;

    @NotNull
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        if (id == null) return false;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return 56557567;
    }
}
