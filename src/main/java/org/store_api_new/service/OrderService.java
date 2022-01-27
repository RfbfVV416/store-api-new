package org.store_api_new.service;

import org.store_api_new.model.Order;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
    Order checkout(HttpSession httpSession);
    void cancelOrder(Long id, HttpSession httpSession);
    List<Order> getAll(HttpSession httpSession);
}
