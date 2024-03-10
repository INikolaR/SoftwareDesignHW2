package ru.hse._223.restaurant.data;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private int price;
    @Column(name = "cooking_time")
    private int cookingTime;
}
