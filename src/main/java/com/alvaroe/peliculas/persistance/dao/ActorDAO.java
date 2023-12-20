package com.alvaroe.peliculas.persistance.dao;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.mapper.ActorMapper;
import com.alvaroe.peliculas.persistance.model.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActorDAO extends JpaRepository<ActorEntity, Integer> {
    /*List<ActorEntity> getAll(Pageable pageable);
    List<ActorEntity> getAll();*/
    Optional<ActorEntity> findById(int id);
/*
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
    }*/
    /*int countAll();
    ActorEntity save(ActorEntity actorEntity);
    void deleteById(int id);*/
}
