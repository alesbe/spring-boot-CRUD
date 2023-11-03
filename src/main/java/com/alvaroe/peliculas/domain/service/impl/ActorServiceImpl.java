package com.alvaroe.peliculas.domain.service.impl;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.service.ActorService;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.domain.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository repository;

    @Override
    public int create (Actor actor) {
        return repository.insert(actor);
    }

    @Override
    public List<Actor> getAll(Integer page, Integer pageSize) {
        return repository.getAll(page, pageSize);
    }

    @Override
    public List<Actor> getAll() {
        return repository.getAll(null, null);
    }

    @Override
    public Actor findById(int id) {
        Actor actor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));

        return actor;
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }

    @Override
    public void update(Actor actor) {
        repository.findById(actor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actor.getId()));


        repository.update(actor);
    }

    @Override
    public void delete(int id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));

        repository.delete(id);
    }
}
