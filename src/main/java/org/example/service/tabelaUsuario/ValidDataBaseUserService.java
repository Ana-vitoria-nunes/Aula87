package org.example.service.tabelaUsuario;

import org.example.connection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidDataBaseUserService {
    private Connection connection = Connect.fazerConexao();

    public boolean isValidUserId(int id) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
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

    public boolean isValidUserInfo(String cpf, String alias, String email, String senha) {
        return !cpf.isBlank() && !alias.isBlank() && !email.isBlank() && !senha.isBlank();
    }

    public boolean isValidEmail(String email) {
        return email.contains("@") && email.endsWith("@gmail.com");
    }

    public boolean isValidUserCredentials(String alias, String senha) {
        if (alias.isBlank() || senha.isBlank()) {
            System.out.println("O nome de usuário e a senha não podem estar vazios.");
            return false;
        }

        String sql = "SELECT COUNT(*) FROM usuario WHERE nome=? AND senha=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alias);
            preparedStatement.setString(2, senha);
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

    public void userInfoByAlias(String alias) {
        String sql = "SELECT id, cpf, email FROM usuario WHERE nome=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alias);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpf = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                System.out.println("Informações da Conta:\n ID: " + id + " | CPF: " + cpf + " | Nome: " + alias + " | Email: " + email);
            } else {
                System.out.println("Usuário com o nome " + alias + " não encontrado.");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

