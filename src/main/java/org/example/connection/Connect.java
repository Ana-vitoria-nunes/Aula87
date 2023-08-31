package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection fazerConexao() {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MovieFlixDB",
                    "postgres", "123456");
            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
