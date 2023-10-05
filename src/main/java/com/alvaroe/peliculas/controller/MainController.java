package com.alvaroe.peliculas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String getAll() {
        return "Este es el índice. Accede a /movie, /director, /actor para realizar las operaciones.";
    }
}
