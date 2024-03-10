package ru.hse._223.restaurant.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hse._223.restaurant.api.dto.Dish;
import ru.hse._223.restaurant.api.CartApi;
import ru.hse._223.restaurant.exceptions.CartException;
import ru.hse._223.restaurant.exceptions.DishException;
import ru.hse._223.restaurant.services.CartService;
import ru.hse._223.restaurant.services.DishService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/cart")
public class CartController implements CartApi {
    private final CartService cartService;
    private final DishService dishService;

    @Override
    @GetMapping(path = "")
    public String getCart(Model model) {
        List<Dish> dishes = cartService.getDishes();
        model.addAttribute("dishes", dishes);
        model.addAttribute("menuDishes", dishService.getAllDishes());
        model.addAttribute("totalTime", cartService.getTotalTime());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart";
    }

    @Override
    @PostMapping(path = "")
    public String postCart(String name, Model model) {
        List<Dish> dishes = cartService.getDishes();
        try {
            Dish dish = dishService.getDishByName(name);
            cartService.addDish(dish);
        } catch (DishException | CartException e) {
            model.addAttribute("occurred_error", "no such dish");
            return handleError(model);
        } catch (Exception e) {
            return "ErrorPage";
        }
        return "redirect:/cart";
    }

    private String handleError(Model model) {
        return "CartErrorPage";
    }
}
