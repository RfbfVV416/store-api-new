package org.store_api_new.dto;

import lombok.*;
import javax.validation.constraints.Min;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {
    @Min(1)
    private Long quantity;
    @EqualsAndHashCode.Include
    private Long productId;
}
