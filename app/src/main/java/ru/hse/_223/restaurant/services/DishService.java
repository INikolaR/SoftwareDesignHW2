package ru.hse._223.restaurant.services;

import ru.hse._223.restaurant.api.dto.Dish;

import java.util.List;

public interface DishService {
    List<Dish> getAllDishes();
    void addDish(Dish dish);
    Dish deleteDish(int id);
}
