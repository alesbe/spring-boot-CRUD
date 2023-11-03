package com.alvaroe.peliculas.domain.service.impl;

import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.MovieService;
import com.alvaroe.peliculas.domain.repository.MovieRepository;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository repository;

    public List<Movie> getAll(Integer page, Integer pageSize) {
        return repository.getAll(page, pageSize);
    }
    @Override
    public List<Movie> getAll() {
        return repository.getAll(null, null);
    }

    public Movie findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }

    @Override
    public int insert(Movie movie) {
        return repository.insert(movie);
    }
}
