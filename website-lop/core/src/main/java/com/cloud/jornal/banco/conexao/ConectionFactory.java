package com.cloud.jornal.banco.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionFactory {

    private static Connection connection;

    public static Connection getConexao(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        if(connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jornal", "postgres", "postgres");
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }

        return connection;
    }

    public static void main(String args[]){
        System.out.println("Conn: "+getConexao());
    }
}
