package com.alvaroe.peliculas.controller;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.MovieService;
import com.alvaroe.peliculas.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService service;
    private final int LIMIT = 10;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam Optional<Integer> page) {
        List<Movie> data = service.getAll(page);
        Response response = new Response(data, service.countAll(), page, LIMIT);

        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie find(@PathVariable("id") int id) {
        System.out.println(service.findById(id));
        return service.findById(id);
    }
}
