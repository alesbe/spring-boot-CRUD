package com.alvaroe.peliculas.persistance.model;

import com.alvaroe.peliculas.persistance.dao.ActorDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterMovieEntity {
    private int id;
    private String characterName;
    private ActorEntity actorEntity;

    public ActorEntity getActorEntity(Connection connection, ActorDAO actorDAO) {
        if(actorEntity == null) {
            this.actorEntity = actorDAO.findByCharacterMovieId(connection, id);
        }

        return actorEntity;
    }
}
