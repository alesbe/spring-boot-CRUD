package com.alvaroe.peliculas.controller.model.movie;

import com.alvaroe.peliculas.controller.model.actor.ActorListWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorListWeb;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailWeb {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorListWeb director;
    private List<ActorListWeb> actors;
}
