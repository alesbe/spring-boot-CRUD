package com.alvaroe.peliculas.persistance.impl;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.CharacterMovie;
import com.alvaroe.peliculas.domain.repository.CharacterMovieRepository;
import com.alvaroe.peliculas.mapper.CharacterMovieMapper;
import com.alvaroe.peliculas.persistance.dao.CharacterMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class CharacterMovieRepositoryImpl implements CharacterMovieRepository {
    @Autowired
    CharacterMovieDAO characterMovieDAO;

    @Override
    public int insert(CharacterMovie characterMovie, Integer movieId) {
        try (Connection connection = DBUtil.open(true)) {
            return characterMovieDAO.insert(connection,
                    CharacterMovieMapper.mapper.toCharacterMovieEntity(characterMovie),
                    movieId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
