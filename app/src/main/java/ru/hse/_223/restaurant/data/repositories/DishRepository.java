package ru.hse._223.restaurant.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse._223.restaurant.data.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
