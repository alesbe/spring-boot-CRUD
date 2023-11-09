package com.alvaroe.peliculas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterMovie {
    private int id;
    private String characterName;
    private Actor actor;
}
