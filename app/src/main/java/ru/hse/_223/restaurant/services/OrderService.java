package ru.hse._223.restaurant.services;

import ru.hse._223.restaurant.api.dto.Order;
import ru.hse._223.restaurant.exceptions.OrderException;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();
    public void addOrder(CartService cartService) throws OrderException;
    public void cancelOrder(int orderId) throws OrderException;
    public void payOrder(int orderId) throws OrderException;
}
