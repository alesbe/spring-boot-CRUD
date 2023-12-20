package com.alvaroe.peliculas.persistance.impl;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.repository.ActorRepository;
import com.alvaroe.peliculas.mapper.ActorMapper;
import com.alvaroe.peliculas.persistance.dao.ActorDAO;
import com.alvaroe.peliculas.persistance.model.ActorEntity;
import com.alvaroe.peliculas.persistance.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class ActorRepositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDAO;

    @Override
    public List<Actor> getAll(Integer page, Integer pageSize) {
        List<ActorEntity> actorEntities;

        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            actorEntities = actorDAO.findAll(pageable).stream().toList();
        } else {
            actorEntities = actorDAO.findAll();
        }

        return actorEntities.stream().map(ActorMapper.mapper::toActor).toList();
    }

    @Override
    public Optional<Actor> findById(int id) {
        Optional<ActorEntity> actorEntity = actorDAO.findById(id);

        if(actorEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ActorMapper.mapper.toActor(actorEntity.get()));
    }

    /*@Override
    public int insert(Actor actor) {
        return actorDAO.save(ActorMapper.mapper.toActorEntity(actor)).getId();
    }*/

    @Override
    public long count() {
        return actorDAO.count();
    }

    /*@Override
    public void update(Actor actor) {
        actorDAO.save(ActorMapper.mapper.toActorEntity(actor));
    }

    public void delete(int id) {
        actorDAO.deleteById(id);
    }*/
}
