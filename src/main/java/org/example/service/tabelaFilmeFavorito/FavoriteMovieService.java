package org.example.service.tabelaFilmeFavorito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.connection.Connect.fazerConexao;

public class FavoriteMovieService {

    private Statement statement;

    public FavoriteMovieService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFavoriteMovie(String titulo, String imagem) {
        String sql = "INSERT INTO filmefavorito (título, imagem) VALUES ('" + titulo + "', '" + imagem + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Favorite movie " + titulo + " added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFavoriteMovie(int id) {
        String sql = "DELETE FROM filmefavorito WHERE id=" + id;

        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Favorite movie deleted successfully!");
            } else {
                System.out.println("Favorite movie with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listFavoriteMovies() {
        String sql = "SELECT id, título, imagem FROM filmefavorito";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("título");
                String imagem = resultSet.getString("imagem");
                System.out.println("Favorite Movie ID: " + id + " | Title: " + titulo + " | Image: " + imagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
