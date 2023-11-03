package com.alvaroe.peliculas.controller.model.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovieCreateWeb {
    private String title;
    private int year;
    private int runtime;
    private int directorId;
}
