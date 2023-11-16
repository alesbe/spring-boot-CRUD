package com.alvaroe.peliculas.domain.repository;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.CharacterMovie;

public interface CharacterMovieRepository {
    public int insert(CharacterMovie characterMovie, Integer movieId);
}
