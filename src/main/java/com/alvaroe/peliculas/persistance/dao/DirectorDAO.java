package com.alvaroe.peliculas.persistance.dao;

import com.alvaroe.peliculas.db.DBUtil;
import com.alvaroe.peliculas.domain.entity.Director;
import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.ResourceNotFoundException;
import com.alvaroe.peliculas.exception.SQLStatmentException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectorDAO {
    private final int LIMIT = 10;

    public List<Director> getAll(Optional<Integer> page) {
        String SQL = "SELECT * FROM directors";
        if(page.isPresent()) {
            int offset = (page.get()-1) * LIMIT;
            SQL += String.format(" LIMIT %d, %d", offset, LIMIT);
        }
        List<Director> directors = new ArrayList<>();
        try (Connection connection = DBUtil.open(true)){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                directors.add(
                        new Director(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                resultSet.getInt("deathYear")
                        )
                );
            }
            DBUtil.close(connection);
            return directors;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public Director findById(int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open(true)){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return new Director(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        (resultSet.getObject("deathYear") != null)? resultSet.getInt("deathYear") : null
                );
            } else {
                throw new ResourceNotFoundException("Id director: " + id);
            }
        }catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public int insert(Director director) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
        try (Connection connection = DBUtil.open(true)){
            int id = DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
            return id;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        } catch (Exception e) {
            throw e;
        }
    }

    public int countAll() {
        String SQL = "SELECT COUNT(id) FROM directors";
        int count = 0;
        try (Connection connection = DBUtil.open(true)){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            DBUtil.close(connection);
            return count;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    public void update(Director director) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        try (Connection connection = DBUtil.open(true)){
            List<Object> params = new ArrayList<>();
            params.add(director.getName());
            params.add(director.getBirthYear());
            params.add(director.getDeathYear());
            params.add(director.getId());
            DBUtil.update(connection, SQL, params);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void delete(int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";
        try (Connection connection = DBUtil.open(true)){
            DBUtil.delete(connection, SQL, List.of(id));
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
