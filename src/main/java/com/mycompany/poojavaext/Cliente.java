/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poojavaext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 202302255701
 */
public class Cliente {
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private String dataNascimento;
    
    private Connection conexaoMySQL;
    
    public Cliente(Connection conexaoMySQL, int id, String cpf, String nome, String email) {
        this.conexaoMySQL = conexaoMySQL;
        
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }
    
    public Cliente(Connection conexaoMySQL, String cpf, String nome, String email) {
        this.conexaoMySQL = conexaoMySQL;
        
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }
    
    public Cliente(Connection conexaoMySQL, int id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }
           
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public final void save() throws SQLException {
        String sql = "INSERT INTO cliente (cpf, nome, email, dataNascimento) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = conexaoMySQL.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, cpf);
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, dataNascimento);

        // Alterado para executeUpdate(), pois é um INSERT
        preparedStatement.executeUpdate();

        int createdId = 0;
        ResultSet rs = preparedStatement.getGeneratedKeys(); 
        if (rs.next()) { 
            createdId = rs.getInt(1);
        } 
        preparedStatement.close();

        this.id = createdId;
    }
    
    public final void update() throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, email = ?, dataNascimento = ? WHERE id = ?";

        PreparedStatement preparedStatement = conexaoMySQL.prepareStatement(sql);
        
        
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, dataNascimento);
        preparedStatement.setInt(4, id);
        
        
        // Alterado para executeUpdate(), pois é um INSERT
        preparedStatement.executeUpdate();
    }
}
