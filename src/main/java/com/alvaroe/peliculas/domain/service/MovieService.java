package com.alvaroe.peliculas.domain.service;

import com.alvaroe.peliculas.domain.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public List<Movie> getAll(Optional<Integer> page);
    public Movie findById(int id);
    public int countAll();
}
