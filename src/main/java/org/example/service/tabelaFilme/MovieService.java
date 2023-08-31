package org.example.service.tabelaFilme;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.connection.Connect.fazerConexao;

public class MovieService {

    private Statement statement;

    public MovieService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMovie(int codFilme, String titulo, String imagem, String descricao, String genero) {
        String sql = "INSERT INTO filme (cod_filme, título, imagem, descrição, gênero) " +
                "VALUES (" + codFilme + ", '" + titulo + "', '" + imagem + "', '" + descricao + "', '" + genero + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Movie " + titulo + " added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(int codFilme) {
        String sql = "DELETE FROM filme WHERE cod_filme=" + codFilme;

        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Movie deleted successfully!");
            } else {
                System.out.println("Movie with code " + codFilme + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMovie(int codFilme, String titulo, String imagem, String descricao, String genero) {
        String sql = "UPDATE filmes SET título='" + titulo + "', imagem='" + imagem +
                "', descrição='" + descricao + "', gênero='" + genero + "' WHERE cod_filme=" + codFilme;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Movie with code " + codFilme + " updated successfully.");
            } else {
                System.out.println("Movie with code " + codFilme + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listMovies() {
        String sql = "SELECT cod_filme, título, imagem, descrição, gênero FROM filme";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int codFilme = resultSet.getInt("cod_filme");
                String titulo = resultSet.getString("título");
                String imagem = resultSet.getString("imagem");
                String descricao = resultSet.getString("descrição");
                String genero = resultSet.getString("gênero");
                System.out.println("Movie Code: " + codFilme + " | Title: " + titulo +
                        " | Image: " + imagem + " | Description: " + descricao + " | Genre: " + genero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
