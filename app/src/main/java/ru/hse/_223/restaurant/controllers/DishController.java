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

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/dishes")
public class DishController implements DishApi {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DishController.class);

    private final DishService dishService;
    @Override
    @GetMapping(path = "/getAllDishes")
    public String getAllDishes(Model model) {
        List<Dish> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "menu";
    }

    @Override
    @PostMapping(path = "/add")
    public String addDish(Dish dish, Model model) {
        try{
            dishService.addDish(dish);
        } catch (Exception e) {
            return handleError(e.getMessage(), model);
        }
        return "redirect:/dishes/getAllDishes";
    }

    @Override
    @PostMapping(path = "/delete")
    public String deleteDish(String name, Model model) {
        try {
            dishService.deleteDish(name);
        } catch (Exception e) {
            return handleError(e.getMessage(), model);
        }
        return "redirect:/dishes/getAllDishes";
    }
    @Override
    @GetMapping(path = "/delete")
    public String deleteDish() {
        return "deleteDish";
    }

    @GetMapping(path = "/error")
    private String handleError(String err, Model model) {
        model.addAttribute("occurred_error", err);
        return "ErrorPage";
    }
}
