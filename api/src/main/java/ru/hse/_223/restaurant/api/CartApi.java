package ru.hse._223.restaurant.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface CartApi {
    String getCart(Model model);
    String postCart(String name, Model model);
}
