package ru.hse._223.restaurant.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.hse._223.restaurant.api.OrderApi;
import ru.hse._223.restaurant.exceptions.OrderException;
import ru.hse._223.restaurant.services.CartService;
import ru.hse._223.restaurant.services.OrderService;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/orders")
public class OrderController implements OrderApi {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DishController.class);

    private final OrderService orderService;
    private final CartService cartService;

    @Override
    @GetMapping(path = "/getAllOrders")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @Override
    @PostMapping(path = "/add")
    public String addOrder(Model model) {
        try {
            orderService.addOrder(cartService);
        } catch (OrderException e) {
            model.addAttribute("occurred_error", e.getMessage());
            return handleError(model);
        } catch (Exception e) {
            return "redirect:/ErrorPage";
        }
        return "redirect:/orders/getAllOrders";
    }
    public String handleError(Model model) {
        return "orderErrorPage";
    }
    @Override
    @PostMapping(path = "/pay")
    public String payOrder(int orderId, Model model) {
        try {
            orderService.payOrder(orderId);
        } catch (OrderException e) {
            model.addAttribute("occurred_error", e.getMessage());
            return handleError(model);
        } catch (Exception e) {
            return "redirect:/ErrorPage";
        }
        return "redirect:/orders/getAllOrders";
    }
    @Override
    @PostMapping(path = "/cancel")
    public String cancelOrder(int orderId, Model model) {
        try {
            orderService.cancelOrder(orderId);
        } catch (OrderException e) {
            model.addAttribute("occurred_error", e.getMessage());
            return handleError(model);
        } catch (Exception e) {
            return "redirect:/ErrorPage";
        }
        return "redirect:/orders/getAllOrders";
    }
}
