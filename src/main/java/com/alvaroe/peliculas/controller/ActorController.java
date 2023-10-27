package com.alvaroe.peliculas.controller;

import com.alvaroe.peliculas.controller.model.actor.ActorListWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieListWeb;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.ActorService;
import com.alvaroe.peliculas.domain.service.DirectorService;
import com.alvaroe.peliculas.http_response.Response;
import com.alvaroe.peliculas.mapper.ActorMapper;
import com.alvaroe.peliculas.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(ActorController.ACTORS)
public class ActorController {
    public static final String ACTORS = "/actors";

    @Autowired
    ActorService service;
    private final int LIMIT = 10;

    @Value("${application.url}")
    private String urlBase;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(required = false) Integer page) {
        List<Actor> actors = (page != null)? service.getAll(Optional.of(page)) : service.getAll(Optional.empty());
        List<ActorListWeb> actorsWeb = actors.stream()
                .map(actor -> ActorMapper.mapper.toActorListWeb(actor))
                .toList();
        int totalRecords = service.countAll();
        Response response = Response.builder()
                .data(actorsWeb)
                .totalRecords(totalRecords)
                .build();

        if(page != null) {
            response.paginate(page, totalRecords, urlBase);
        }
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
        return Response.builder().data(ActorMapper.mapper.toActorDetailWeb(service.findById(id))).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Actor actor) {
        service.update(id, actor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
