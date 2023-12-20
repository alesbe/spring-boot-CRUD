package com.alvaroe.peliculas.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    private int id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Actor(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
}
