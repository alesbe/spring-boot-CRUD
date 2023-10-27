package com.alvaroe.peliculas.controller.model.actor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActorCreateWeb {
    private String name;
    private int birthYear;
    private Integer deathYear;
}
