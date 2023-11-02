package com.alvaroe.peliculas.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorEntity {

    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;
}
