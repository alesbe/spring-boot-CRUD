package com.alvaroe.peliculas.domain.repository;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    public List<Actor> getAll(Integer page, Integer pageSize);
    public Optional<Actor> findById(int id);
    //ublic int insert(Actor actor);
    public long count();
    /*public void update(Actor actor);
    public void delete(int id);*/
}
