package com.alvaroe.peliculas.domain.service.impl;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.service.ActorService;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.persistance.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository repository;

    @Override
    public List<Actor> getAll(Optional<Integer> page) {
        return repository.getAll(page);
    }

    @Override
    public Actor findById(int id) {
        return repository.findById(id);
    }

    @Override
    public int create (Actor actor) {
        return repository.insert(actor);
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }

    @Override
    public void update(int id, Actor actor) {
        Actor existingActor = repository.findById(id);
        if (existingActor == null) {
            throw new ResourceNotFoundException("Actor not found with id: " + id);
        }
        actor.setId(existingActor.getId());
        repository.update(actor);
    }

    @Override
    public void delete(int id) {
        Actor actor = repository.findById(id);
        if (actor == null) {
            throw new ResourceNotFoundException("Actor not found with id: " + id);
        }
        repository.delete(id);
    }
}
