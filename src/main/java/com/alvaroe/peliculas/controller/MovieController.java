package com.alvaroe.peliculas.controller;

import com.alvaroe.peliculas.controller.model.movie.MovieCreateWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieListWeb;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.MovieService;
import com.alvaroe.peliculas.http_response.Response;
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
@RequestMapping(MovieController.MOVIES)
public class MovieController {
    public static final String MOVIES = "/movies";

    @Autowired
    private MovieService service;

    @Value("${page.size}")
    private int PAGE_SIZE;

    @Value("${application.url}")
    private String urlBase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        List<Movie> movies = (page != null) ? service.getAll(page, pageSize) : service.getAll();
        List<MovieListWeb> moviesWeb = movies.stream()
                .map(movie -> MovieMapper.mapper.toMovieListWeb(movie))
                .toList();
        int totalRecords = service.countAll();
        Response response = Response.builder()
                .data(moviesWeb)
                .totalRecords(totalRecords)
                .build();

        if(page != null) {
            response.paginate(page, totalRecords, urlBase);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWeb) {
        int id = service.insert(MovieMapper.mapper.toMovie(movieCreateWeb));

        return Response.builder()
                .data(MovieMapper.mapper.toMovieDetailWeb(movieCreateWeb))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(service.findById(id))).build();
    }
}
