package com.alvaroe.peliculas.controller.model.director;

import com.alvaroe.peliculas.controller.model.actor.ActorListWeb;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectorDetailWeb {
    private int id;
    private String name;
    private int birthYear;
    private int deathYear;
}
