package ru.hse._223.restaurant.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Dish {
    private String name;
    private int amount;
    private double price;
    private int cooking_time;
}
