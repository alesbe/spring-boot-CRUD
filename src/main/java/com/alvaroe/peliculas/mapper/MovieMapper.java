package com.alvaroe.peliculas.mapper;

import com.alvaroe.peliculas.controller.model.movie.MovieCreateWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieDetailWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieListWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieUpdateWeb;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.CharacterMovie;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.persistance.model.CharacterMovieEntity;
import com.alvaroe.peliculas.persistance.model.DirectorEntity;
import com.alvaroe.peliculas.persistance.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    MovieListWeb toMovieListWeb(Movie movie);
    MovieDetailWeb toMovieDetailWeb(Movie movie);
    MovieDetailWeb toMovieDetailWeb(MovieCreateWeb movieCreateWeb);

    @Mapping(target = "director", expression = "java(mapDirectorEntityToDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characterMovies", expression = "java(mapCharacterMovieEntitiesToCharacterMovies(movieEntity.getCharacterMovieEntities()))")
    Movie toMovie(MovieEntity movieEntity);
    Movie toMovie(MovieUpdateWeb movieUpdateWeb);

    @Mapping(target = "director", ignore = true)
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    @Mapping(target = "directorEntity", expression = "java(mapDirectorToDirectorEntity(movie.getDirector()))")
    MovieEntity toMovieEntity(Movie movie);

    default List<Integer> mapActorsToActorIds(List<Actor> actors) {
        return actors.stream()
                .map(actor -> actor.getId())
                .toList();
    }

    default DirectorEntity mapDirectorToDirectorEntity(Director director) {
        return DirectorMapper.mapper.toDirectorEntity(director);
    }

    default Director mapDirectorEntityToDirector(DirectorEntity directorEntity) {
        return DirectorMapper.mapper.toDirector(directorEntity);
    }

    default List<CharacterMovie> mapCharacterMovieEntitiesToCharacterMovies(List<CharacterMovieEntity> characterMovieEntities) {
        if(characterMovieEntities == null) return null;

        return characterMovieEntities.stream()
                .map(CharacterMovieMapper.mapper::toCharacterMovie)
                .toList();
    }

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;
}
