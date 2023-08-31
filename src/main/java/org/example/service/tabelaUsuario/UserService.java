package org.example.service.tabelaUsuario;

import org.example.connection.Connect;

import java.sql.*;
import static org.example.connection.Connect.fazerConexao;

public class UserService {

    private Statement statement;
    private Connection connection = Connect.fazerConexao();

    public UserService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String cpf, String nameUser, String email, String senha) {
        String sql = "INSERT INTO usuario (cpf, nome, email, senha) " +
                "VALUES ('" + cpf + "', '" + nameUser + "', '" + email + "', '" + senha + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("User " + nameUser + " added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM usuario WHERE id=" + id;

        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id,String email, String senha) {
        String sql = "UPDATE usuario SET email='" + email + "', senha='" + senha + "' WHERE id=" + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("User with ID " + id + " updated successfully.");
            } else {
                System.out.println("User with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listUsers() {
        String sql = "SELECT id, cpf, nome, email FROM usuario";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpf = resultSet.getString("cpf");
                String nameUser = resultSet.getString("nome");
                String email = resultSet.getString("email");
                System.out.println("User ID: " + id + " | CPF: " + cpf +
                        " | Name: " + nameUser + " | Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean isValidUserInfo(String cpf, String nameUser, String email, String senha) {
        return !cpf.isBlank() && !nameUser.isBlank() && !email.isBlank() && !senha.isBlank();
    }

    public boolean isValidUserCredentials(String nameUser, String senha) {
        if (nameUser.isBlank() || senha.isBlank()) {
            System.out.println("O nome de usuário e a senha não podem estar vazios.");
            return false;
        }

        String sql = "SELECT COUNT(*) FROM usuario WHERE nome='" + nameUser + "' AND senha='" + senha + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int count = resultSet.getInt(1);

            resultSet.close();
            statement.close();

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
