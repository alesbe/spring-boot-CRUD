package com.alvaroe.peliculas.domain.service;

import com.alvaroe.peliculas.controller.model.characterMovie.CharacterMovieUpdateWeb;
import com.alvaroe.peliculas.domain.entity.CharacterMovie;
import com.alvaroe.peliculas.domain.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {
    // Create
    public int create(Movie movie, Integer directorId, List<Map<String, Object>> characters);

    public int addCharacter(Integer actorId, Integer movieId, CharacterMovie characterMovie);

    public int addCharacters(Integer actorId, Integer movieId, List<CharacterMovie> characterMovies);

    // Read
    public List<Movie> getAll(Integer page, Integer pageSize);
    public List<Movie> getAll();
    public Movie findById(int id);
    public int countAll();

    // Update
    public void update(Movie movie, Integer directorId, List<Map<String, Object>> characters);
    public void updateCharacter(CharacterMovie characterMovie, int characterId, int actorId);

    // Delete
    public void delete(int id);
    public void deleteCharacter(int characterId);
}
