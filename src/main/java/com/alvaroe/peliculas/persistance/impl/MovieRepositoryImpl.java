package com.alvaroe.peliculas.persistance.impl;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.domain.repository.MovieRepository;
import com.alvaroe.peliculas.persistance.dao.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;
    private final int LIMIT = 10;

    @Override
    public List<Movie> getAll(Optional<Integer> page) {
        return movieDAO.getAll(page);
    }

    @Override
    public Movie findById(int id) {
        return movieDAO.findById(id);
    }

    @Override
    public int countAll() {
        return movieDAO.countAll();
    }
}
