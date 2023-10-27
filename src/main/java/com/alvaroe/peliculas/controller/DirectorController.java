package com.alvaroe.peliculas.controller;

import com.alvaroe.peliculas.controller.model.actor.ActorListWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorCreateWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorDetailWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorListWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorUpdateWeb;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.DirectorService;
import com.alvaroe.peliculas.http_response.Response;
import com.alvaroe.peliculas.mapper.ActorMapper;
import com.alvaroe.peliculas.mapper.DirectorMapper;
import com.alvaroe.peliculas.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(DirectorController.DIRECTORS)
public class DirectorController {
    public static final String DIRECTORS = "/directors";

    @Autowired
    DirectorService service;
    private final int LIMIT = 10;

    @Value("${application.url}")
    private String urlBase;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(required = false) Integer page) {
        List<Director> directors = (page != null) ? service.getAll(Optional.of(page)) : service.getAll(Optional.empty());
        List<DirectorListWeb> directorsWeb = directors.stream()
                .map(director -> DirectorMapper.mapper.toDirectorListWeb(director))
                .toList();
        int totalRecords = service.countAll();
        Response response = Response.builder()
                .data(directorsWeb)
                .totalRecords(totalRecords)
                .build();

        if (page != null) {
            response.paginate(page, totalRecords, urlBase);
        }

        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody DirectorCreateWeb directorCreateWeb){
        int id = service.create(DirectorMapper.mapper.toDirector(directorCreateWeb));
        DirectorDetailWeb directorDetailWeb = new DirectorDetailWeb(
                id,
                directorCreateWeb.getName(),
                directorCreateWeb.getBirthYear(),
                directorCreateWeb.getDeathYear()
        );
        return Response.builder().data(directorDetailWeb).build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(DirectorMapper.mapper.toDirectorDetailWeb(service.findById(id))).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody DirectorUpdateWeb directorUpdateWeb) {
        directorUpdateWeb.setId(id);
        service.update(DirectorMapper.mapper.toDirector(directorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
