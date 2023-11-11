package com.alvaroe.peliculas.domain.service.impl;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.CharacterMovie;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.repository.ActorRepository;
import com.alvaroe.peliculas.domain.repository.DirectorRepository;
import com.alvaroe.peliculas.domain.service.MovieService;
import com.alvaroe.peliculas.domain.repository.MovieRepository;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public int create(Movie movie, Integer directorId, List<Map<String, Object>> characters) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));

        List<CharacterMovie> characterMovies = new ArrayList<>();

        characters.forEach(character -> {
            CharacterMovie characterMovie = new CharacterMovie();

            character.forEach((key, value) -> {
                switch (key) {
                    case "actorId":
                        characterMovie.setActor(actorRepository.findById((Integer) value)
                                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + value)));

                        break;

                    case "characterName":
                        characterMovie.setCharacterName((String) value);
                        break;
                }
            });

            characterMovies.add(characterMovie);
        });

        movie.setDirector(director);
        movie.setCharacterMovies(characterMovies);

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
    public void update(Movie movie, Integer directorId, Map<Integer, String> characters) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));

        List<CharacterMovie> characterMovies = new ArrayList<>();

        characters.forEach((actorId, characterName) -> {
            CharacterMovie characterMovie = new CharacterMovie();

            characterMovie.setActor(
                    actorRepository.findById(actorId)
                            .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId))
            );

            characterMovie.setCharacterName(characterName);

            characterMovies.add(characterMovie);
        });

        movie.setDirector(director);
        movie.setCharacterMovies(characterMovies);

        repository.update(movie);
    }

    @Override
    public void delete(int id) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        repository.delete(movie);
    }
}
