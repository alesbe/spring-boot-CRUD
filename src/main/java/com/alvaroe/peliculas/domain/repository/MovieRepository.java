package com.alvaroe.peliculas.domain.repository;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.CharacterMovie;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.exception.SQLStatmentException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    public List<Movie> getAll(Integer page, Integer pageSize);
    public Optional<Movie> findById(int id);
    public int countAll();
    public int insert(Movie movie);
    public int insertCharacter(CharacterMovie characterMovie, Integer movieId);
    public void update(Movie movie);
    public void delete(Movie movie);
}
