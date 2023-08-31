package org.example.service.tabelaUsuario;

import org.example.connection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidUserAdminService {
    private Connection connection = Connect.fazerConexao();

    private boolean isAdmin(String alias) {
        return alias.equals("José");
    }

    public boolean isValidUserCredentials(String alias, String senha) {
        if (alias.isBlank() || senha.isBlank()) {
            throw new IllegalArgumentException("O nome de usuário e a senha não podem estar vazios.");
        }

        if (isAdmin(alias)) {
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

                if (count > 0) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Credenciais de administrador inválidas.");
                }
            } catch (SQLException e) {
                // Handle the exception appropriately, e.g., log it
                e.printStackTrace();
                throw new RuntimeException("Erro ao verificar as credenciais do usuário.");
            }
        } else {
            throw new IllegalArgumentException("Acesso não autorizado. Você não é um administrador.");
        }
    }
}

