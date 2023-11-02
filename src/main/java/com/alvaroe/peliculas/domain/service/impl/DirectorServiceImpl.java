package com.alvaroe.peliculas.domain.service.impl;

import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.service.DirectorService;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.domain.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    DirectorRepository repository;

    @Override
    public List<Director> getAll(Optional<Integer> page) {
        return repository.getAll(page);
    }

    @Override
    public Director findById(int id) {
        return repository.findById(id);
    }

    @Override
    public int create(Director director) {
        return repository.insert(director);
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }

    @Override
    public void update(Director director) {
        Director existingDirector = repository.findById(director.getId());
        if (existingDirector == null) {
            throw new ResourceNotFoundException("Director not found with id: " + director.getId());
        }
        director.setId(existingDirector.getId());
        repository.update(director);
    }

    @Override
    public void delete(int id) {
        Director director = repository.findById(id);
        if (director == null) {
            throw new ResourceNotFoundException("Director not found with id: " + id);
        }
        repository.delete(id);
    }
}
