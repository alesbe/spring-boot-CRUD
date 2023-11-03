package com.alvaroe.peliculas.domain.service;

import com.alvaroe.peliculas.domain.entity.Movie;

import java.util.List;

public interface MovieService {
    // Create
    public int create(Movie movie, Integer directorId, List<Integer> actorIds);

    // Read
    public List<Movie> getAll(Integer page, Integer pageSize);
    public List<Movie> getAll();
    public Movie findById(int id);
    public int countAll();

    // Update
    public void update(Movie movie);

    // Delete
    public void delete(int id);
}
