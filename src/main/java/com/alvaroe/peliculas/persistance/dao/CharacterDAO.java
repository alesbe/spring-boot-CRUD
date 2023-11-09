package com.alvaroe.peliculas.persistance.dao;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import com.alvaroe.peliculas.mapper.CharacterMovieMapper;
import com.alvaroe.peliculas.mapper.DirectorMapper;
import com.alvaroe.peliculas.mapper.MovieMapper;
import com.alvaroe.peliculas.persistance.model.CharacterMovieEntity;
import com.alvaroe.peliculas.persistance.model.DirectorEntity;
import com.alvaroe.peliculas.persistance.model.MovieEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CharacterDAO {
    public List<CharacterMovieEntity> findByMovieId(Connection connection, int movieId) {
        final String SQL = "SELECT * FROM actors_movies WHERE movie_id = ?";

        List<CharacterMovieEntity> characterMovieEntities = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            while (resultSet.next()) {
                //characterMovieEntities.add(CharacterMovieMapper.mapper.toCharacterMovie(resultSet));
            }
            return characterMovieEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
