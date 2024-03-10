package ru.hse._223.restaurant.controllers;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping(path = "")
public class CustomErrorController implements ErrorController {
    @GetMapping(path = "/error")
    public String error() {
        return "error";
    }
}
