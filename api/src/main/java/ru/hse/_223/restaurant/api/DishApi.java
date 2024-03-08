package ru.hse._223.restaurant.api;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.hse._223.restaurant.api.dto.Dish;
import org.springframework.ui.Model;

import java.util.List;

public interface DishApi {
    public String getAllDishes(Model model);

    String addDish(Dish dish, RedirectAttributes redirectAttributes);

    public String deleteDish(int id);
}
