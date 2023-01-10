package com.example.restdoc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compute")
public class ComputeController {

    @GetMapping("/root/{number}")
    public Double root(@PathVariable Double number) {
        return Math.sqrt(number);
    }

    @GetMapping("/square/{number}")
    public double square(@PathVariable Double number) {
        return number * number;
    }
}