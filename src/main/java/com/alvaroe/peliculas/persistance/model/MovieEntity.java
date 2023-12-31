package com.alvaroe.peliculas.persistance.model;

import com.alvaroe.peliculas.persistance.dao.CharacterMovieDAO;
import com.alvaroe.peliculas.persistance.dao.DirectorDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
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
    private DirectorEntity directorEntity;
    private List<CharacterMovieEntity> characterMovieEntities;

    public MovieEntity(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

    public DirectorEntity getDirectorEntity(Connection connection, DirectorDAO directorDAO) {
        if(directorEntity == null) {
            this.directorEntity = directorDAO.findByMovieId(connection, id).orElse(null);
        }

        return directorEntity;
    }

    public List<CharacterMovieEntity> getCharacterMovieEntities(Connection connection, CharacterMovieDAO characterMovieDAO) {
        if(characterMovieEntities == null) {
            this.characterMovieEntities = characterMovieDAO.findByMovieId(connection, id);
        }

        return characterMovieEntities;
    }
}