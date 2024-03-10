package ru.hse._223.restaurant.services;

import org.springframework.stereotype.Service;
import ru.hse._223.restaurant.data.Order;
import ru.hse._223.restaurant.data.enums.OrderStatus;
import ru.hse._223.restaurant.exceptions.OrderException;
import ru.hse._223.restaurant.mappers.OrderMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private class Cooker implements Runnable {
        private Order currentOrder;
        @Override
        public void run() {
            while(true) {
                currentOrder = queueOrders.poll();
                if (currentOrder == null) {
                    continue;
                }
                currentOrder.setOrderStatus(OrderStatus.PROCESSING);
                while (currentOrder.getRemainingTime() > 0) {
                    try {
                        Thread.sleep(1000);
                        currentOrder.setRemainingTime(currentOrder.getRemainingTime() - 1);
                    } catch (Exception e) {
                        System.out.println("EXCEPTION OCCURRED");
                    }
                }
                if (currentOrder.getRemainingTime() < 0) {
                    currentOrder.setOrderStatus(OrderStatus.CANCELLED);
                    currentOrder.setRemainingTime(0);
                } else {
                    currentOrder.setOrderStatus(OrderStatus.FINISHED);
                }
            }
        }
    }

    private final List<ru.hse._223.restaurant.data.Order> orders = new ArrayList<>(100);
    private final BlockingQueue<Order> queueOrders = new ArrayBlockingQueue<>(100);
    private ArrayList<Thread> cookers = new ArrayList<Thread>(10);
    private final int nOfCookers = 5;
    private int currOrderId = 0;
    private final OrderMapper orderMapper;
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
        for (int i = 0; i < nOfCookers; ++i) {
            cookers.add(new Thread(new Cooker()));
        }
        for (int i = 0; i < nOfCookers; ++i) {
            cookers.get(i).start();
        }
    }

    @Override
    public List<ru.hse._223.restaurant.api.dto.Order> getAllOrders() {
        return orders.stream().map(orderMapper::mapDataToDto).collect(Collectors.toList());
    }

    public void addOrder(CartService cartService) throws OrderException{
        if (cartService.isCartEmpty()) {
            throw new OrderException("Order is empty");
        }
        Order dataOrder = new Order(currOrderId++, "user", cartService.getTotalTime(), cartService.getTotalPrice(), OrderStatus.CREATED);
        cartService.clear();
        orders.add(dataOrder);
        queueOrders.add(dataOrder);
    }

    public void cancelOrder(int orderId) throws OrderException{
        if (orders.stream().noneMatch(o -> o.getId() == orderId)) {
            throw new OrderException("No such order");
        }
        Order order = orders.stream().filter(o -> o.getId() == orderId).toList().get(0);
        if (order.getOrderStatus() == OrderStatus.PAYED) {
            throw new OrderException("Order has been payed, cannot cancel");
        }
        order.setRemainingTime(-1);
        order.setOrderStatus(OrderStatus.CANCELLED);
    }

    public void payOrder(int orderId) throws OrderException {
        if (orders.stream().noneMatch(o -> o.getId() == orderId)) {
            throw new OrderException("No such order");
        }
        Order order = orders.stream().filter(o -> o.getId() == orderId).toList().get(0);
        if (order.getOrderStatus() != OrderStatus.FINISHED) {
            throw new OrderException("Order is not finished yet");
        }
        order.setOrderStatus(OrderStatus.PAYED);
    }
}
