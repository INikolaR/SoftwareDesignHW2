package ru.hse._223.restaurant.services;

import ru.hse._223.restaurant.api.dto.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();
    public void addOrder(Order order);
}
