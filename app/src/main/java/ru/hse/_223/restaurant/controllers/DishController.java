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
    public String addDish(Dish dish, RedirectAttributes redirectAttributes) {
        System.out.println("CALLED ADDDISH");
        try{
            dishService.addDish(dish);
            System.out.println("TIME: " + dish.getCookingTime());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/dishes/getAllDishes";
    }

    @Override
    @PostMapping(path = "/delete")
    public String deleteDish(int id) {
        return "";
    }
}
