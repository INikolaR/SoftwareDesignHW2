package ru.hse._223.restaurant.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse._223.restaurant.api.dto.Dish;
import ru.hse._223.restaurant.exceptions.CartException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final DishService dishService;
    CartServiceImpl(DishService dishService) {
        this.dishService = dishService;
    }
    private List<Dish> dishes = new ArrayList<>(1000);

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) throws CartException {
        try {
            dishService.decreaseDishAmount(dish.getName());
        } catch (Exception e) {
            throw new CartException(e.getMessage());
        }
        dishes.add(dish);
    }

    public int getTotalTime() {
        return dishes.stream().mapToInt(Dish::getCookingTime).sum();
    }

    public int getTotalPrice() {
        return dishes.stream().mapToInt(Dish::getPrice).sum();
    }

    @Override
    public void clear() {
        dishes.clear();
    }
    @Override
    public Boolean isCartEmpty() {
        return dishes.isEmpty();
    }
}
