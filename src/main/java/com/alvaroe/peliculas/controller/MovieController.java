package com.alvaroe.peliculas.controller;

import com.alvaroe.peliculas.controller.model.movie.MovieCreateWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieListWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieUpdateWeb;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.MovieService;
import com.alvaroe.peliculas.http_response.Response;
import com.alvaroe.peliculas.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(MovieController.MOVIES)
public class MovieController {
    public static final String MOVIES = "/movies";

    @Autowired
    private MovieService movieService;

    @Value("${page.size}")
    private int PAGE_SIZE;

    @Value("${application.url}")
    private String urlBase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWeb) {
        int id = movieService.create(MovieMapper.mapper.toMovie(movieCreateWeb),
                movieCreateWeb.getDirectorId(),
                movieCreateWeb.getCharacters());

        MovieListWeb movieListWeb = new MovieListWeb();
        movieListWeb.setTitle(movieCreateWeb.getTitle());
        movieListWeb.setId(id);

        return Response.builder()
                .data(movieListWeb)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null) ? pageSize : PAGE_SIZE;
        List<Movie> movies = (page != null) ? movieService.getAll(page, pageSize) : movieService.getAll();
        List<MovieListWeb> moviesWeb = movies.stream()
                .map(movie -> MovieMapper.mapper.toMovieListWeb(movie))
                .toList();
        int totalRecords = movieService.countAll();
        Response response = Response.builder()
                .data(moviesWeb)
                .totalRecords(totalRecords)
                .build();

        if(page != null) {
            response.paginate(page, pageSize, urlBase);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movieService.findById(id))).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody MovieUpdateWeb movieUpdateWeb) {
        movieUpdateWeb.setId(id);
        movieService.update(
                MovieMapper.mapper.toMovie(movieUpdateWeb),
                movieUpdateWeb.getDirectorId(),
                movieUpdateWeb.getCharacters()
        );
    }


}
