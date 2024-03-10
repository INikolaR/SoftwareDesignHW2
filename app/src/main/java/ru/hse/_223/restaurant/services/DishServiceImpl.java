package ru.hse._223.restaurant.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse._223.restaurant.api.dto.Dish;
import ru.hse._223.restaurant.data.repositories.DishRepository;
import ru.hse._223.restaurant.exceptions.DishException;
import ru.hse._223.restaurant.mappers.DishMapper;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    @Override
    public List<Dish> getAllDishes() {
        List<ru.hse._223.restaurant.data.Dish> dishes = dishRepository.findAll();
        return dishes.stream().map(dishMapper::mapDataToDto).sorted(new Comparator<Dish>() {
            @Override
            public int compare(Dish dish, Dish t1) {
                return dish.getName().compareTo(t1.getName());
            }
        }).collect(Collectors.toList());
    }

    @Override
    public void addDish(Dish dish) throws DishException {
        if (dishRepository.findById(dish.getName()).isPresent()) {
            throw new DishException("Dish with name " + dish.getName() + " already exists!");
        }
        dishRepository.save(dishMapper.mapDtoToData(dish));
    }

    @Override
    public void deleteDish(String name) throws DishException {
        Optional<ru.hse._223.restaurant.data.Dish> optionalDish = dishRepository.findById(name);
        if (optionalDish.isEmpty()) {
            throw new DishException("No dish with name " + name + "!");
        }
        dishRepository.deleteById(name);
    }

    @Override
    public Dish getDishByName(String name) throws DishException {
        Optional<ru.hse._223.restaurant.data.Dish> optionalDish = dishRepository.findById(name);
        if (optionalDish.isEmpty()) {
            throw new DishException("No such dish");
        }
        return dishMapper.mapDataToDto(optionalDish.get());
    }

    @Override
    public void decreaseDishAmount(String name) throws DishException {
        Optional<ru.hse._223.restaurant.data.Dish> optionalDish = dishRepository.findById(name);
        if (optionalDish.isEmpty()) {
            throw new DishException("No such dish");
        }
        ru.hse._223.restaurant.data.Dish dish = optionalDish.get();
        if (dish.getAmount() <= 0) {
            throw new DishException("Cannot decrease quantity of dish " + dish.getName() + " because it is 0");
        }
        dish.setAmount(dish.getAmount() - 1);
        dishRepository.deleteById(name);
        dishRepository.save(dish);
    }
}
