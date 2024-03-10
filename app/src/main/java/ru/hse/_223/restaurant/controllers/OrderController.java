package ru.hse._223.restaurant.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.hse._223.restaurant.api.DishApi;
import ru.hse._223.restaurant.api.dto.Dish;
import org.springframework.ui.Model;
import ru.hse._223.restaurant.services.DishService;
import ru.hse._223.restaurant.services.OrderService;
import ru.hse._223.restaurant.api.dto.Order;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/orders")
public class OrderController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DishController.class);

    private final OrderService orderService;

    @GetMapping(path = "/getAllOrders")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @PostMapping(path = "/add")
    public String addOrder(Order order, Model model) {
        orderService.addOrder(order);
        return "redirect:/orders/getAllOrders";
    }
}
