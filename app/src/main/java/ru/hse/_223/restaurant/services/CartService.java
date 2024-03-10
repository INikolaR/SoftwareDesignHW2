package ru.hse._223.restaurant.services;

import ru.hse._223.restaurant.api.dto.Dish;
import ru.hse._223.restaurant.exceptions.CartException;

import java.util.List;

public interface CartService {
    public List<Dish> getDishes();
    public void addDish(Dish dish) throws CartException;
    public int getTotalTime();
    public int getTotalPrice();
    public void clear();
    public Boolean isCartEmpty();
}
