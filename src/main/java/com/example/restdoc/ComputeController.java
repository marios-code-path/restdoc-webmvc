package com.example.restdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

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


    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(RequestPredicates.GET("/ping")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                                .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
                        (req) -> ServerResponse
                                .status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body("pong"));
    }
}