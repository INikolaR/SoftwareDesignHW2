package ru.hse._223.restaurant.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse._223.restaurant.api.dto.Dish;
import ru.hse._223.restaurant.data.repositories.DishRepository;
import ru.hse._223.restaurant.mappers.DishMapper;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    @Override
    public List<Dish> getAllDishes() {
        List<ru.hse._223.restaurant.data.Dish> dishes = dishRepository.findAll();
        return dishes.stream().map(dishMapper::mapDataToDto).collect(Collectors.toList());
    }

    @Override
    public void addDish(Dish dish) {
        dishRepository.save(dishMapper.mapDtoToData(dish));
    }

    @Override
    public Dish deleteDish(int id) {
        return null;
    }
}
