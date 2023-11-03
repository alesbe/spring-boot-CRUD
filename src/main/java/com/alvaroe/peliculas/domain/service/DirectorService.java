package com.alvaroe.peliculas.domain.service;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    // Create
    public int create(Director director);

    // Read
    public List<Director> getAll(Integer page, Integer pageSize);
    public List<Director> getAll();
    public Director findById(int id);
    public int countAll();

    // Update
    public void update(Director director);

    // Delete
    public void delete(int id);
}
