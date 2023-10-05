package com.alvaroe.peliculas.persistance;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorRepository {
    public List<Director> getAll(Optional<Integer> page);
    public Director findById(int id);
    public int insert(Director director);
    public int countAll();
    public void update(Director director);
}
