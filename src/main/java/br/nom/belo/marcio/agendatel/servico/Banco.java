package br.nom.belo.marcio.agendatel.servico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {

    private Banco() { 
        // para impedir 
    }

    public static Connection getConexao() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "agenda");
    }

}