package com.alvaroe.peliculas.domain.service.impl;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.CharacterMovie;
import com.alvaroe.peliculas.domain.repository.ActorRepository;
import com.alvaroe.peliculas.domain.repository.CharacterMovieRepository;
import com.alvaroe.peliculas.domain.service.CharacterMovieService;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterMovieServiceImpl implements CharacterMovieService {
    @Autowired
    CharacterMovieRepository characterMovieRepository;

    @Autowired
    ActorRepository actorRepository;

    @Override
    public int create(Integer actorId, Integer movieId, CharacterMovie characterMovie) {
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId));

        characterMovie.setActor(actor);

        return characterMovieRepository.insert(characterMovie, movieId);
    }

    @Override
    public int create(Integer actorId, Integer movieId, List<CharacterMovie> characterMovies) {
        return 0;
    }
}
