package ru.hse._223.restaurant.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.hse._223.restaurant.api.dto.Dish;
import org.springframework.ui.Model;

import java.util.List;

public interface DishApi {
    public String getAllDishes(Model model);
    String getAllDishesAdmin(Model model);
    public String addDish(Dish dish, Model model);
    public String deleteDish(String name, Model model);
    public String editDish(Dish dish, Model model);
}
