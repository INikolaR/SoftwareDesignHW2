package ru.hse._223.restaurant.api.dto;

import lombok.*;

@Getter
@Setter
@Data
public class Order {
    private int id;
    private String userName;
    private int time;
    private int price;
    private String status;
}