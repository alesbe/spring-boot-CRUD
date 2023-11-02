package com.alvaroe.peliculas.domain.repository;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    public List<Actor> getAll(Integer page, Integer pageSize);
    public Optional<Actor> findById(int id);
    public int insert(Actor actor);
    public int countAll();
    public void update(Actor actor);
    public void delete(int id);
}
