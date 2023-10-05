package com.alvaroe.peliculas.controller;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.DirectorService;
import com.alvaroe.peliculas.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/director")
public class DirectorController {
    @Autowired
    DirectorService service;
    private final int LIMIT = 10;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam Optional<Integer> page) {
        List<Director> data = service.getAll(page);
        Response response = new Response(data, service.countAll(), page, LIMIT);

        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Director create(@RequestBody Director director){
        int id = service.create(director);
        director.setId(id);
        return director;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response find(@PathVariable("id") int id) {
        Director data = service.findById(id);
        Response response = new Response(data, service.countAll(), Optional.empty(), LIMIT);

        return response;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Director director) {
        service.update(id, director);
    }
}
