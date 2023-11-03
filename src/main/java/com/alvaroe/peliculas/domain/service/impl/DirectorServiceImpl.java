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
    public int create(Director director) {
        return repository.insert(director);
    }

    @Override
    public List<Director> getAll(Integer page, Integer pageSize) {
        return repository.getAll(page, pageSize);
    }

    @Override
    public List<Director> getAll() {
        return repository.getAll(null, null);
    }

    @Override
    public Director findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }

    @Override
    public void update(Director director) {
        repository.findById(director.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + director.getId()));

        repository.update(director);
    }

    @Override
    public void delete(int id) {
        repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));

        repository.delete(id);
    }
}
