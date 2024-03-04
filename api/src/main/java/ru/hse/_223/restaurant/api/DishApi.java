package ru.hse._223.restaurant.api;

import ru.hse._223.restaurant.api.dto.Dish;

import java.util.List;

public interface DishApi {
    public List<Dish> getAllDishes();
    public void addDish(Dish d);
    public void deleteDish(int id);
}
