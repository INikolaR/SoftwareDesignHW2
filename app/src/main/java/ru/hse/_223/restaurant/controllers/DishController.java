package ru.hse._223.restaurant.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hse._223.restaurant.api.DishApi;
import ru.hse._223.restaurant.api.dto.Dish;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/dishes")
public class DishController implements DishApi {
    @Override
    @GetMapping
    @ResponseBody
    public List<Dish> getAllDishes() {
        List<Dish> d = new ArrayList<Dish>();
        d.add(Dish.builder().name("T").price(1000).amount(3).cooking_time(4).build());
        return d;
    }

    @Override
    public void addDish(Dish d) {

    }

    @Override
    public void deleteDish(int id) {

    }
}
