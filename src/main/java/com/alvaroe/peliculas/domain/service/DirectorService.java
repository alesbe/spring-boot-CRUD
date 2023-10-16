package com.alvaroe.peliculas.domain.service;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    public List<Director> getAll(Optional<Integer> page);
    public Director findById(int id);
    public int create(Director director);
    public int countAll();
    public void update(int id, Director director);
    public void delete(int id);
}
