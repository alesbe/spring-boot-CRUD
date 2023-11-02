package com.alvaroe.peliculas.mapper;

import com.alvaroe.peliculas.controller.model.director.DirectorCreateWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorDetailWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorListWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorUpdateWeb;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.persistance.model.DirectorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
    DirectorListWeb toDirectorListWeb(Director director);
    DirectorDetailWeb toDirectorDetailWeb(Director director);
    DirectorEntity toDirectorEntity(ResultSet resultSet);
}
