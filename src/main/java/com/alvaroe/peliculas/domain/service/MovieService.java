package com.alvaroe.peliculas.domain.service;

import com.alvaroe.peliculas.domain.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {
    // Create
    public int create(Movie movie, Integer directorId, List<Map<String, Object>> characters);

    // Read
    public List<Movie> getAll(Integer page, Integer pageSize);
    public List<Movie> getAll();
    public Movie findById(int id);
    public int countAll();

    // Update
    public void update(Movie movie, Integer directorId, List<Map<String, Object>> characters);

    // Delete
    public void delete(int id);
}
