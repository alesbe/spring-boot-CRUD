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

    public void updateMovieCharacters(Connection connection, List<CharacterMovieEntity> characterMovieEntities) {
        final String SQL = "UPDATE actors_movies SET actor_id = ?, characters = ? WHERE id = ?";

        characterMovieEntities.forEach(character -> {
            List<Object> params = new ArrayList<>();

            params.add(character.getActorEntity().getId());
            params.add(character.getCharacterName());
            params.add(character.getId());

            DBUtil.update(connection, SQL, params);
        });
    }

    public void insertMovieCharacters(Connection connection, List<CharacterMovieEntity> characterMovieEntities, Integer movieId) throws SQLException {
        final String SQL = "INSERT INTO actors_movies (movie_id, actor_id, characters) VALUES (?, ?, ?)";

        try {
            characterMovieEntities.forEach(character -> {
                List<Object> params = new ArrayList<>();

                params.add(movieId);
                params.add(character.getActorEntity().getId());
                params.add(character.getCharacterName());

                DBUtil.insert(connection, SQL, params);
            });

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException("No se ha podido añadir personajes para la pelicula con el id: " + movieId);
        }
    }

    public int insert(Connection connection, CharacterMovieEntity characterMovieEntity, Integer movieId) throws SQLException {
        List<Object> params = new ArrayList<>();
        final String SQL = "INSERT INTO actors_movies(movie_id, actor_id, characters) VALUES (?, ?, ?)";

        params.add(movieId);
        params.add(characterMovieEntity.getActorEntity().getId());
        params.add(characterMovieEntity.getCharacterName());

        int id = DBUtil.insert(connection, SQL, params);

        return id;
    }
}
