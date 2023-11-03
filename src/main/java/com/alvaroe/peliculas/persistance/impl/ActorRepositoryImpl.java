package com.alvaroe.peliculas.persistance.impl;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.domain.repository.ActorRepository;
import com.alvaroe.peliculas.mapper.ActorMapper;
import com.alvaroe.peliculas.persistance.dao.ActorDAO;
import com.alvaroe.peliculas.persistance.model.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDAO;

    @Override
    public List<Actor> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<ActorEntity> actorEntities = actorDAO.getAll(connection, page, pageSize);
            List<Actor> actors = actorEntities.stream()
                    .map(actorEntity -> ActorMapper.mapper.toActor(actorEntity))
                    .toList();
            return actors;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Actor> findById(int id) {
        try (Connection connection = DBUtil.open(true)) {
            Optional<ActorEntity> actorEntity = actorDAO.findById(connection, id);

            if(actorEntity.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(ActorMapper.mapper.toActor(actorEntity.get()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Actor actor) {
        try (Connection connection = DBUtil.open(true)) {
            return actorDAO.insert(connection, ActorMapper.mapper.toActorEntity(actor));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countAll() {
        try (Connection connection = DBUtil.open(true)) {
            return actorDAO.countAll(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Actor actor) {
        try (Connection connection = DBUtil.open(true)) {
            actorDAO.update(connection, actor);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (Connection connection = DBUtil.open(true)) {
            actorDAO.delete(connection, id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
