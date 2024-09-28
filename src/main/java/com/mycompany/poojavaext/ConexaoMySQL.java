/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poojavaext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 202302255701
 */
public class ConexaoMySQL {
    public Connection getConexaoMySQL() {
        Connection connection;

        try {
            // Carregando o JDBC Driver padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);

            // Configurando a nossa conexão com um banco de dados
            String serverName = "localhost";    //caminho do servidor do BD
            String mydatabase = "poojavaext";        //nome do seu banco de dados
            String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase;
            String username = "root";        //nome de um usuário de seu BD
            String password = "";      //sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);
            
            // Testa a conexão
            if (connection != null) {
                System.out.println("STATUS--->Conectado com sucesso!");
            } else {
                System.out.println("STATUS--->Não foi possivel realizar conexão");
            }
            return connection;
            
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            System.out.println(e);
            return null;
        }
        
    }
}
