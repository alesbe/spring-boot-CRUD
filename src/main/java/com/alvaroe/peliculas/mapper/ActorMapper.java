package com.alvaroe.peliculas.mapper;

import com.alvaroe.peliculas.controller.model.actor.ActorCreateWeb;
import com.alvaroe.peliculas.controller.model.actor.ActorDetailWeb;
import com.alvaroe.peliculas.controller.model.actor.ActorListWeb;
import com.alvaroe.peliculas.controller.model.actor.ActorUpdateWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieDetailWeb;
import com.alvaroe.peliculas.controller.model.movie.MovieListWeb;
import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.Movie;
import com.alvaroe.peliculas.persistance.model.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    Actor toActor(ActorCreateWeb actorCreateWeb);
    Actor toActor(ActorUpdateWeb actorUpdateWeb);
    Actor toActor(ActorEntity actorEntity);
    ActorListWeb toActorListWeb(Actor actor);
    ActorDetailWeb toActorDetailWeb(Actor actor);
    ActorEntity toActorEntity(Actor actor);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;
}
