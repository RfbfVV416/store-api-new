package org.store_api_new.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputItem {
    private String productTitle;

    private Long quantity;

    public OutputItem(String productTitle, Long quantity) {
        this.productTitle = productTitle;
        this.quantity = quantity;
    }
}
