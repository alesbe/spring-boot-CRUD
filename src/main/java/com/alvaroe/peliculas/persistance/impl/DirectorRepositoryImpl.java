package com.alvaroe.peliculas.persistance.impl;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.domain.repository.DirectorRepository;
import com.alvaroe.peliculas.persistance.dao.DirectorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;
    private final int LIMIT = 10;

    public List<Director> getAll(Optional<Integer> page) {
        return directorDAO.getAll(page);
    }

    @Override
    public Director findById(int id) {
        return directorDAO.findById(id);
    }

    @Override
    public int insert(Director director) {
        return directorDAO.insert(director);
    }

    @Override
    public int countAll() {
        return directorDAO.countAll();
    }

    @Override
    public void update(Director director) {
        directorDAO.update(director);
    }

    public void delete(int id) {
        directorDAO.delete(id);
    }
}
