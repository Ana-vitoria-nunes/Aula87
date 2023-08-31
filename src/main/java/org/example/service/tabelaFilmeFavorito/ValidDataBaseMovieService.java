package org.example.service.tabelaFilmeFavorito;

import org.example.connection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidDataBaseMovieService {
    private Connection connection = Connect.fazerConexao();

    public boolean isValidMovieCode(String codeMovie) {
        String sql = "SELECT COUNT(*) FROM movies WHERE code=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codeMovie);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            resultSet.close();
            preparedStatement.close();

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isValidMovieInfo(String codeMovie, String title, String image, String description, String gender) {
        return !codeMovie.isEmpty() && !title.isEmpty() && !image.isEmpty() && !description.isEmpty() && !gender.isEmpty();
    }

    public boolean isValidMovieToAddToFavorite(String title) {
        String sql = "SELECT COUNT(*) FROM movies WHERE title=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            resultSet.close();
            preparedStatement.close();

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

