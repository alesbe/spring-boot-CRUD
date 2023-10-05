package com.alvaroe.peliculas.controller;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.service.ActorService;
import com.alvaroe.peliculas.domain.service.DirectorService;
import com.alvaroe.peliculas.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    ActorService service;
    private final int LIMIT = 10;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam Optional<Integer> page) {
        List<Actor> data = service.getAll(page);
        Response response = new Response(data, service.countAll(), page, LIMIT);

        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Actor create(@RequestBody Actor actor){
        int id = service.create(actor);
        actor.setId(id);
        return actor;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response find(@PathVariable("id") int id) {
        Actor data = service.findById(id);
        Response response = new Response(data, service.countAll(), Optional.empty(), LIMIT);

        return response;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Actor actor) {
        service.update(id, actor);
    }
}
