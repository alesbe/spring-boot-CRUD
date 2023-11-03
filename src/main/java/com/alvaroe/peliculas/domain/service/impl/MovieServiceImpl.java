package com.alvaroe.peliculas.domain.service.impl;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.repository.ActorRepository;
import com.alvaroe.peliculas.domain.repository.DirectorRepository;
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

    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    ActorRepository actorRepository;

    @Override
    public int create(Movie movie, Integer directorId, List<Integer> actorIds) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));

        List<Actor> actors = actorIds.stream()
                .map(actorId -> actorRepository.findById(actorId).orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId)))
                .toList();

        movie.setDirector(director);
        movie.setActors(actors);

        return repository.insert(movie);
    }

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
    public void update(Movie movie) {
        repository.findById(movie.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movie.getId()));

        repository.update(movie);
    }

    @Override
    public void delete(int id) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        repository.delete(movie);
    }
}
