package com.alvaroe.peliculas.persistance.dao;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.mapper.MovieMapper;
import com.alvaroe.peliculas.persistance.model.MovieEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieDAO {
    public List<MovieEntity> getAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String sql = "SELECT * FROM movies";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<MovieEntity> movieEntities = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                movieEntities.add(MovieMapper.mapper.toMovieEntity(resultSet));
            }
            return movieEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Optional<MovieEntity> findById(Connection connection, int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return Optional.of(MovieMapper.mapper.toMovieEntity(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public int countAll(Connection connection) {
        String SQL = "SELECT COUNT(id) FROM movies";
        int count = 0;
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            DBUtil.close(connection);
            return count;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public int insert(Connection connection, MovieEntity movieEntity) throws SQLException {
        try {
            List<Object> params = new ArrayList<>();
            String SQL = "INSERT INTO movies (title, year, runtime, director_id) VALUES (?, ?, ?, ?)";

            params.add(movieEntity.getTitle());
            params.add(movieEntity.getYear());
            params.add(movieEntity.getRuntime());
            params.add(movieEntity.getDirectorId());

            int movieId = DBUtil.insert(connection, SQL, params);

            movieEntity.getActorIds().stream()
                    .forEach(actorId -> addActor(connection, movieId, actorId));

            connection.commit();
            return movieId;
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    public void addActor(Connection connection, int movieId, int actorId) {
        String SQL = "INSERT INTO actors_movies (movie_id, actor_id) VALUES (?, ?)";
        DBUtil.insert(connection, SQL, List.of(movieId, actorId));
    }

    public void update(Connection connection, MovieEntity movieEntity) {
        final String SQL = "UPDATE movies SET title = ?, year = ?, runtime = ?, director_id = ? WHERE id = ?";

        List<Object> params = new ArrayList<>();
        params.add(movieEntity.getTitle());
        params.add(movieEntity.getYear());
        params.add(movieEntity.getRuntime());
        params.add(movieEntity.getDirectorId());
        params.add(movieEntity.getId());

        DBUtil.update(connection, SQL, params);
    }

    public void delete(Connection connection, MovieEntity movieEntity) {
        final String SQL = "DELETE FROM movies WHERE id = ?";

        DBUtil.delete(connection, SQL, List.of(movieEntity.getId()));
    }
}
