package com.alvaroe.peliculas.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterMovieEntity {
    private int id;
    private String characterName;
    private ActorEntity actorEntity;
}
