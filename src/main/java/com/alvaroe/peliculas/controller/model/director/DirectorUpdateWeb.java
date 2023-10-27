package com.alvaroe.peliculas.controller.model.director;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectorUpdateWeb {
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;
}
