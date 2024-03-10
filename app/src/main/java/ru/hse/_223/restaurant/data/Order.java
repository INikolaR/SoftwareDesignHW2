package ru.hse._223.restaurant.data;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.hse._223.restaurant.data.enums.OrderStatus;

@Data
@AllArgsConstructor
@Builder
public class Order {
    private int id;
    private String userName;
    private int remainingTime;
    private int price;
    private OrderStatus orderStatus;
}
