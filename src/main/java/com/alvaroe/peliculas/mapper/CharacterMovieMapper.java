package com.alvaroe.peliculas.mapper;

import com.alvaroe.peliculas.domain.entity.Actor;
import com.alvaroe.peliculas.domain.entity.CharacterMovie;
import com.alvaroe.peliculas.persistance.model.CharacterMovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMovieMapper {
    CharacterMovieMapper mapper = Mappers.getMapper(CharacterMovieMapper.class);

    //@Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    //@Mapping(target = "characterName", expression = "java(resultSet.getString(\"characters\"))")
    //CharacterMovie toCharacterMovie(ResultSet resultSet);
}
