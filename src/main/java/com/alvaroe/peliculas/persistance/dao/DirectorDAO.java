package com.alvaroe.peliculas.persistance.dao;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.mapper.ActorMapper;
import com.alvaroe.peliculas.mapper.DirectorMapper;
import com.alvaroe.peliculas.persistance.model.ActorEntity;
import com.alvaroe.peliculas.persistance.model.DirectorEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DirectorDAO {
    public List<DirectorEntity> getAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String SQL = "SELECT * FROM directors";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            SQL += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<DirectorEntity> directorEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, params);
            while (resultSet.next()) {
                directorEntities.add(
                        DirectorMapper.mapper.toDirectorEntity(resultSet)
                );
            }

            return directorEntities;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public Optional<DirectorEntity> findById(Connection connection, int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if(resultSet.next()) {
                 return Optional.of(DirectorMapper.mapper.toDirectorEntity(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public Optional<DirectorEntity> findByMovieId(Connection connection, int movieId) {
        final String SQL = "SELECT d.* FROM directors d INNER JOIN  movies m ON m.director_id = d.id WHERE m.id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            if(resultSet.next()) {
                return Optional.of(DirectorMapper.mapper.toDirectorEntity(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public int insert(Connection connection, DirectorEntity directorEntity) {
        List<Object> params = new ArrayList<>();
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";

        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());

        int id = DBUtil.insert(connection, SQL, params);

        return id;
    }

    public int countAll(Connection connection) {
        String SQL = "SELECT COUNT(id) FROM directors";
        int count = 0;
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public void update(Connection connection, DirectorEntity directorEntity) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";

        List<Object> params = new ArrayList<>();
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());
        params.add(directorEntity.getId());
        DBUtil.update(connection, SQL, params);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";

        DBUtil.delete(connection, SQL, List.of(id));
    }
}
