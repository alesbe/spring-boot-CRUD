package com.alvaroe.peliculas.mapper;

import com.alvaroe.peliculas.controller.model.director.DirectorDetailWeb;
import com.alvaroe.peliculas.controller.model.director.DirectorListWeb;
import com.alvaroe.peliculas.domain.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    DirectorListWeb toDirectorListWeb(Director director);
    DirectorDetailWeb toDirectorDetailWeb(Director director);
}
