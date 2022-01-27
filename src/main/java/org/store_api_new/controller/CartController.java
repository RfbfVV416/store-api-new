package org.store_api_new.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.store_api_new.dto.Cart;
import org.store_api_new.dto.OutputCart;
import org.store_api_new.dto.Item;
import org.store_api_new.service.CartService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public OutputCart get(HttpSession httpSession){
        return cartService.getCart(httpSession);
    }

    @PostMapping
    public Cart addItem(@RequestBody @Valid Item item, HttpSession httpSession){
        return cartService.addItemToCart(item, httpSession);
    }

    @PutMapping
    public Cart updateItem(@RequestBody @Valid Item item, HttpSession httpSession){
        return cartService.updateItemInCart(item, httpSession);
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable Long id, HttpSession httpSession){
        cartService.deleteItemFromCart(id, httpSession);
    }
}
