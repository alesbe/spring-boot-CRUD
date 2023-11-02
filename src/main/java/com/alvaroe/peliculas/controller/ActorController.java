package com.alvaroe.peliculas.controller;

import com.alvaroe.peliculas.controller.model.actor.ActorCreateWeb;
import com.alvaroe.peliculas.controller.model.actor.ActorDetailWeb;
import com.alvaroe.peliculas.controller.model.actor.ActorListWeb;
import com.alvaroe.peliculas.controller.model.actor.ActorUpdateWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorCreateWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorDetailWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorUpdateWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieListWeb;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.ActorService;
import com.alvaroe.peliculas.domain.service.DirectorService;
import com.alvaroe.peliculas.http_response.Response;
import com.alvaroe.peliculas.mapper.ActorMapper;
import com.alvaroe.peliculas.mapper.DirectorMapper;
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

    @Value("${application.url}")
    private String urlBase;

    @Value("${page.size}")
    private int PAGE_SIZE;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : PAGE_SIZE;
        List<Actor> actors = (page != null)? service.getAll(page, pageSize) : service.getAll();
        List<ActorListWeb> actorsWeb = actors.stream()
                .map(actor -> ActorMapper.mapper.toActorListWeb(actor))
                .toList();
        int totalRecords = service.countAll();
        Response response = Response.builder()
                .data(actorsWeb)
                .totalRecords(totalRecords)
                .build();

        if(page != null) {
            response.paginate(page, pageSize, urlBase);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody ActorCreateWeb actorCreateWeb){
        int id = service.create(ActorMapper.mapper.toActor(actorCreateWeb));
        ActorDetailWeb actorDetailWeb = new ActorDetailWeb(
                id,
                actorCreateWeb.getName(),
                actorCreateWeb.getBirthYear(),
                actorCreateWeb.getDeathYear()
        );
        return Response.builder().data(actorDetailWeb).build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(ActorMapper.mapper.toActorDetailWeb(service.findById(id))).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody ActorUpdateWeb actorUpdateWeb) {
        actorUpdateWeb.setId(id);
        service.update(ActorMapper.mapper.toActor(actorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
