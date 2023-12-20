package com.alvaroe.peliculas.domain.service;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.http_response.Response;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    // Create
    /*int create(Actor actor);*/

    // Read
    public List<Actor> getAll(Integer page, Integer pageSize);
    public List<Actor> getAll();
    public Actor findById(int id);
    public long count();

    // Update
    /*public void update(Actor actor);

    // Delete
    public void delete(int id);*/
}
