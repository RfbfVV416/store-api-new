package org.store_api_new.dto;

import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class Cart {
    private Set<Item> cartItems = new HashSet<>();

   public boolean addItem(Item item){ return cartItems.add(item); }

   public boolean delItem(Item item){ return cartItems.remove(item); }

    public boolean delItemById(Long productId) {
       return cartItems.removeIf((x) -> productId.equals(x.getProductId()));
   }
}
