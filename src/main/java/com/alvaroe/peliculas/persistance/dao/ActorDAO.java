package com.alvaroe.peliculas.persistance.dao;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.mapper.ActorMapper;
import com.alvaroe.peliculas.persistance.model.ActorEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ActorDAO {
    public List<ActorEntity> getAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String sql = "SELECT * FROM actors";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<ActorEntity> actorEntities = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                System.out.println(resultSet);
                actorEntities.add(ActorMapper.mapper.toActorEntity(resultSet));
            }
            return actorEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Optional<ActorEntity> findById(Connection connection, int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if(resultSet.next()) {
                return Optional.of(ActorMapper.mapper.toActorEntity(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public ActorEntity findByCharacterMovieId(Connection connection, int characterMovieId) {
        final String SQL = "SELECT a.* FROM actors_movies am JOIN actors a ON am.actor_id = a.id WHERE am.id = ? LIMIT 1;";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(characterMovieId));
            if(resultSet.next()) {
                return ActorMapper.mapper.toActorEntity(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public int countAll(Connection connection) {
        String SQL = "SELECT COUNT(id) FROM actors";
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

    public int insert(Connection connection, ActorEntity actorEntity) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());

        int id = DBUtil.insert(connection, SQL, params);
        return id;
    }

    public void update(Connection connection, Actor actor) {
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";

        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        params.add(actor.getId());
        DBUtil.update(connection, SQL, params);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM actors WHERE id = ?";

        DBUtil.delete(connection, SQL, List.of(id));
    }
}
