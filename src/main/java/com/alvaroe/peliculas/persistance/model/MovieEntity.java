package com.alvaroe.peliculas.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private int directorId;
    private List<Integer> actorIds;


    public MovieEntity(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }
}