package ru.hse._223.restaurant.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Dish {
    public Dish(){}
    private String name;
    private int amount;
    private int price;
    private int cookingTime;
}
