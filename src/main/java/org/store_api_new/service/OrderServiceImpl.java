package org.store_api_new.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.store_api_new.constant.SessionAttributeName;
import org.store_api_new.dto.Cart;
import org.store_api_new.dto.Item;
import org.store_api_new.exception.EmptyCartOrderException;
import org.store_api_new.exception.OrderModificationException;
import org.store_api_new.exception.NotFoundEntityException;
import org.store_api_new.model.Order;
import org.store_api_new.model.Product;
import org.store_api_new.repository.OrderRepository;
import org.store_api_new.repository.ProductRepository;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.store_api_new.model.OrderStatus.CANCELED;
import static org.store_api_new.model.OrderStatus.NEW;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Order checkout(HttpSession httpSession) {
        Cart cart = cartService.getCartFromSession(httpSession);
        if (cart.getCartItems().isEmpty()){
            throw new EmptyCartOrderException("Your cart is empty");
        }

        BigDecimal subtotal = new BigDecimal(0);
        for (Item item: cart.getCartItems()) {
            subtotal = subtotal
                    .add(updateProductIfPossible(item.getQuantity(), item.getProductId()).getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        Order order = new Order();
        order.setOrderStatus(NEW);
        order.setDate(LocalDate.now());
        order.setTotalPrice(subtotal);
        order.setUser(userService.getUserBySessionEmail(httpSession));

        httpSession.setAttribute(SessionAttributeName.CART, new Cart());
        return orderRepository.save(order);
    }

    private Product updateProductIfPossible(Long quantity, Long productId){
        Product product = productService.getById(productId);
        Long available = product.getAvailable();
        productService.checkAvailability(quantity, available);
        product.setAvailable(available - quantity);
        return productRepository.save(product);
    }

    @Override
    public void cancelOrder(Long id, HttpSession httpSession) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("There is no order with id " + id));
        if (!order.getUser().equals(userService.getUserBySessionEmail(httpSession))){
            throw new OrderModificationException(HttpStatus.BAD_REQUEST, "You not allowed to modify another user order");
        }
        if (CANCELED.equals(order.getOrderStatus())){
            throw new OrderModificationException(HttpStatus.CONFLICT, "This order already canceled");
        }
        order.setOrderStatus(CANCELED);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAll(HttpSession httpSession) {
        return orderRepository.findByUser(userService.getUserBySessionEmail(httpSession));
    }
}
