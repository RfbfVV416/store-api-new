package org.store_api_new.service;

import org.store_api_new.dto.Cart;
import org.store_api_new.dto.OutputCart;
import org.store_api_new.dto.Item;
import javax.servlet.http.HttpSession;

public interface CartService {
    OutputCart getCart(HttpSession httpSession);
    Cart addItemToCart(Item item, HttpSession httpSession);
    Cart updateItemInCart(Item item, HttpSession httpSession);
    void deleteItemFromCart(Long id, HttpSession httpSession);
    Cart getCartFromSession(HttpSession httpSession);
}
