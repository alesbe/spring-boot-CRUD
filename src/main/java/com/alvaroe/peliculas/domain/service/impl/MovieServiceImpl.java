package com.alvaroe.peliculas.domain.service.impl;

import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.service.MovieService;
import com.alvaroe.peliculas.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository repository;

    public List<Movie> getAll(Optional<Integer> page) {
        return repository.getAll(page);
    }

    public Movie findById(int id) {
        return repository.findById(id);
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }
}
