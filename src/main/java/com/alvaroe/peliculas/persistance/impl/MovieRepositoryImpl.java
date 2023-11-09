package com.alvaroe.peliculas.persistance.impl;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.domain.repository.MovieRepository;
import com.alvaroe.peliculas.mapper.MovieMapper;
import com.alvaroe.peliculas.persistance.dao.CharacterDAO;
import com.alvaroe.peliculas.persistance.dao.DirectorDAO;
import com.alvaroe.peliculas.persistance.dao.MovieDAO;
import com.alvaroe.peliculas.persistance.model.MovieEntity;
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

    @Autowired
    DirectorDAO directorDAO;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<MovieEntity> movieEntities = movieDAO.getAll(connection, page, pageSize);
            List<Movie> movies = movieEntities.stream()
                    .map(movie -> MovieMapper.mapper.toMovie(movie))
                    .toList();

            connection.close();
            return movies;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> findById(int id) {
        try(Connection connection = DBUtil.open(true)) {
            Optional<MovieEntity> movieEntity = movieDAO.findById(connection, id);

            if(movieEntity.isEmpty()) {
                connection.close();
                return Optional.empty();
            }

            movieEntity.get().getDirectorEntity(connection, directorDAO);

            //movieEntity.get().getCharacters(connection, characterDAO);

            connection.close();
            return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countAll() {
        try(Connection connection = DBUtil.open(true)) {
            connection.close();
            return movieDAO.countAll(connection);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Movie movie) {
        try(Connection connection = DBUtil.open(false)) {
            connection.close();
            return movieDAO.insert(connection, MovieMapper.mapper.toMovieEntity(movie));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Movie movie) {
        try(Connection connection = DBUtil.open(true)) {
            movieDAO.update(connection, MovieMapper.mapper.toMovieEntity(movie));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Movie movie) {
        try(Connection connection = DBUtil.open(true)) {
            movieDAO.delete(connection, MovieMapper.mapper.toMovieEntity(movie));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
