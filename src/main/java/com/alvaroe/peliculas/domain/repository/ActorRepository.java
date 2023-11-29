package com.alvaroe.peliculas.domain.repository;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    // Create
    public int insert(Actor actor);

    // Read
    public List<Actor> getAll(Integer page, Integer pageSize);
    public Optional<Actor> findById(int id);
    public int countAll();

    // Update
    public void update(Actor actor);

    // Delete
    public void delete(int id);
}
