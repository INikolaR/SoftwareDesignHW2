package ru.hse._223.restaurant.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface OrderApi {
    String getAllOrders(Model model);
    String addOrder(Model model);
    String payOrder(int orderId, Model model);
    String cancelOrder(int orderId, Model model);
}
