package com.alvaroe.peliculas.controller.model.actor;

import com.alvaroe.peliculas.controller.model.director.DirectorListWeb;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ActorDetailWeb {
    private int id;
    private String name;
    private int birthYear;
    private int deathYear;
}
