package com.alvaroe.peliculas.controller.model.movie;

import com.alvaroe.peliculas.domain.entity.CharacterMovie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MovieUpdateWeb {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private int directorId;
    private Map<Integer, String> characters;
}
