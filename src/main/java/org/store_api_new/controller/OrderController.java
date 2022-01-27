package org.store_api_new.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.store_api_new.model.Order;
import org.store_api_new.service.OrderService;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Order checkout(HttpSession httpSession){
        return orderService.checkout(httpSession);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public void cancel(@PathVariable Long id, HttpSession httpSession){
        orderService.cancelOrder(id, httpSession);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Order> getAll(HttpSession httpSession){
        return orderService.getAll(httpSession);
    }
}
