package com.alvaroe.peliculas.domain.service;

import com.alvaroe.peliculas.domain.entity.CharacterMovie;

import java.util.List;

public interface CharacterMovieService {
    // Create
    public int create(Integer actorId, Integer movieId, CharacterMovie characterMovie);
    public int create(Integer actorId, Integer movieId, List<CharacterMovie> characterMovies);
}
