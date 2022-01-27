package org.store_api_new.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.store_api_new.constant.SessionAttributeName;
import org.store_api_new.dto.Cart;
import org.store_api_new.dto.OutputCart;
import org.store_api_new.dto.Item;
import org.store_api_new.dto.OutputItem;
import org.store_api_new.exception.EntityAlreadyExistsException;
import org.store_api_new.exception.NotFoundEntityException;
import org.store_api_new.model.Product;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final ProductService productService;

    @Override
    public OutputCart getCart(HttpSession httpSession) {
        Cart cart = getCartFromSession(httpSession);
        OutputCart outputCart = new OutputCart();
        BigDecimal subtotal = new BigDecimal(0);

        for (Item item: cart.getCartItems()) {
            Product product = productService.getById(item.getProductId());
            Long quantity = item.getQuantity();
            outputCart.addItem(
                    new OutputItem(
                           product.getTitle(),
                            quantity
                    )
            );
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        outputCart.setSubtotal(subtotal);

        return outputCart;
    }

    @Override
    public Cart addItemToCart(Item item, HttpSession httpSession) {
        Cart cart = getCartFromSession(httpSession);
        verifyItem(item);
        if (!cart.addItem(item)){
            throw new EntityAlreadyExistsException("This item already exists");
        }
        return cart;
    }

    @Override
    public Cart updateItemInCart(Item item, HttpSession httpSession) {
        Cart cart = getCartFromSession(httpSession);
        if (!cart.delItem(item)){
            throw new NotFoundEntityException("Item not found");
        }
        verifyItem(item);
        cart.addItem(item);
        return cart;
    }

    private void verifyItem(Item item){
        Product product = productService.getById(item.getProductId());
        productService.checkAvailability(item.getQuantity(), product.getAvailable());
    }

    @Override
    public void deleteItemFromCart(Long id, HttpSession httpSession) {
        Cart cart = getCartFromSession(httpSession);
        if (!cart.delItemById(id)){
            throw new NotFoundEntityException("There is no item with id " + id);
        }
    }

    @Override
    public Cart getCartFromSession(HttpSession httpSession) {
        return (Cart) httpSession.getAttribute(SessionAttributeName.CART);
    }
}



