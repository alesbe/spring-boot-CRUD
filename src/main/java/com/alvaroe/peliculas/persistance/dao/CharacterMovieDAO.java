package com.alvaroe.peliculas.persistance.dao;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.mapper.CharacterMovieMapper;
import com.alvaroe.peliculas.persistance.model.CharacterMovieEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMovieDAO {
    public List<CharacterMovieEntity> findByMovieId(Connection connection, int movieId) {
        List<CharacterMovieEntity> characterMovieEntities = new ArrayList<>();
        final String SQL = "SELECT c.* FROM actors_movies c INNER JOIN movies m ON m.id = c.movie_id AND m.id = ?";

        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            if(!resultSet.next()) {
                return null;
            }
            do {
                characterMovieEntities.add(CharacterMovieMapper.mapper.toCharacterMovieEntity(resultSet));
            } while (resultSet.next());
            return characterMovieEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}