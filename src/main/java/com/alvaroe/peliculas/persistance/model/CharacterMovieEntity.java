package com.alvaroe.peliculas.persistance.model;

import com.alvaroe.peliculas.persistance.dao.ActorDAO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Connection;

@Entity
@Table(name = "actors_movies")
@Data
@NoArgsConstructor
public class CharacterMovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "characters")
    private String characterName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private ActorEntity actorEntity;

    /*
    public ActorEntity getActorEntity(Connection connection, ActorDAO actorDAO) {
        if(actorEntity == null) {
            this.actorEntity = actorDAO.findByCharacterMovieId(connection, id);
        }

        return actorEntity;
    }*/
}
