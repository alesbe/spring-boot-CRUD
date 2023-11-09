package com.alvaroe.peliculas.db;

import com.alvaroe.peliculas.exception.DBConnectionException;
import com.alvaroe.peliculas.exception.SQLStatmentException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class DBUtil {

    private static final String URL_CONNECTION = "jdbc:mariadb://localhost:3306/movies";
    private static final String USERNAME = "movies";
    private static final String PASSWORD = "1234";

    public static Connection open(boolean autoCommit){
        try {
            Connection connection = DriverManager.getConnection(
                    URL_CONNECTION,
                    USERNAME,
                    PASSWORD
            );
            connection.setAutoCommit(autoCommit);
            return connection;
        } catch (SQLException e) {
            throw new DBConnectionException("Connection paramaters :\n\n" + getParameters() + "\nOriginal exception message: " + e.getMessage());
        }
    }

    private static String getParameters (){
        return String.format("url: %s\nUser: %s\nPassword: %s\n",
                URL_CONNECTION,
                USERNAME,
                PASSWORD
        );
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBConnectionException("Can't close connection");
        }
    }

    public static ResultSet select(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException("Error executing sql statement: " + sql);
        }
    }

    public static int insert(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getInt(1);
            } else {
                throw new RuntimeException("Cannot read last generated id");
            }
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + sql);
        }
    }


    public static int update(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            int numRows = preparedStatement.executeUpdate();
            return numRows;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + sql);
        }
    }

    private static PreparedStatement setParameters(Connection connection, String sql, List<Object> values){
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(values != null) {
                for(int i=0;i<values.size();i++) {
                    Object value = values.get(i);
                    preparedStatement.setObject(i+1,value);
                }
            }
            return preparedStatement;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static int delete(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + sql);
        }
    }

}