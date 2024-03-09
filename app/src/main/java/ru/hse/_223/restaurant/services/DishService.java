package ru.hse._223.restaurant.services;

import ru.hse._223.restaurant.api.dto.Dish;
import ru.hse._223.restaurant.exceptions.DishException;

import java.util.List;

public interface DishService {
    List<Dish> getAllDishes();
    void addDish(Dish dish) throws DishException;
    void deleteDish(String name) throws DishException;
}
