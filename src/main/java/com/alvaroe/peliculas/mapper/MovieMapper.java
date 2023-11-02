package com.alvaroe.peliculas.mapper;

import com.alvaroe.peliculas.controller.model.movie.MovieDetailWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieListWeb;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.persistance.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    MovieListWeb toMovieListWeb(Movie movie);
    MovieDetailWeb toMovieDetailWeb(Movie movie);
    MovieEntity toMovieEntity(ResultSet resultSet);
}
