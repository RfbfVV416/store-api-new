package org.store_api_new.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Hashtable;

@Data
public class OutputCart {
    private Hashtable<Long, OutputItem> cartItems = new Hashtable<>();

    private BigDecimal subtotal;

    public void addItem(OutputItem item){
        cartItems.put((long) cartItems.size(), item);
    }
}
