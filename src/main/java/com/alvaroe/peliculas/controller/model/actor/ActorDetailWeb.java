package com.alvaroe.peliculas.controller.model.actor;

import com.alvaroe.peliculas.controller.model.director.DirectorListWeb;
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
public class ActorDetailWeb {
    private int id;
    private String name;
    private int birthYear;
    private int deathYear;
}
