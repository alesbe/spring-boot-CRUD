package com.alvaroe.peliculas.mapper;

import com.alvaroe.peliculas.controller.model.movie.MovieCreateWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieDetailWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieListWeb;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Movie;
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
    Movie toMovie(MovieEntity movieEntity);
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "actors", ignore = true)
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    @Mapping(target = "directorId", expression = "java(movie.getDirector().getId())")
    @Mapping(target = "actorIds", expression = "java(mapActorsToActorIds(movie.getActors()))")
    MovieEntity toMovieEntity(Movie movie);

    default List<Integer> mapActorsToActorIds(List<Actor> actors) {
        return actors.stream()
                .map(actor -> actor.getId())
                .toList();
    }

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;
}
