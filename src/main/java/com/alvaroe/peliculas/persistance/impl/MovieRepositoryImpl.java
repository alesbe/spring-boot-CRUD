package com.alvaroe.peliculas.persistance.impl;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.domain.repository.MovieRepository;
import com.alvaroe.peliculas.mapper.MovieMapper;
import com.alvaroe.peliculas.persistance.dao.CharacterMovieDAO;
import com.alvaroe.peliculas.persistance.dao.DirectorDAO;
import com.alvaroe.peliculas.persistance.dao.MovieDAO;
import com.alvaroe.peliculas.persistance.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    @Autowired
    MovieDAO movieDAO;

    @Autowired
    DirectorDAO directorDAO;

    @Autowired
    CharacterMovieDAO characterMovieDAO;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<MovieEntity> movieEntities = movieDAO.getAll(connection, page, pageSize);
            List<Movie> movies = movieEntities.stream()
                    .map(movie -> MovieMapper.mapper.toMovie(movie))
                    .toList();

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
                return Optional.empty();
            }

            movieEntity.get().getDirectorEntity(connection, directorDAO);

            movieEntity.get().getCharacterMovieEntities(connection, characterMovieDAO);

            return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countAll() {
        try(Connection connection = DBUtil.open(true)) {
            return movieDAO.countAll(connection);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Movie movie) {
        try(Connection connection = DBUtil.open(false)) {
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
