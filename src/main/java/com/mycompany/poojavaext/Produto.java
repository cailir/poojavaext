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
public class Produto {
    private int id;
    private int codigo;
    private String descricao;
    private float valorVenda;
    private int quantidadeEstoque;
    
    private final Connection conexaoMySQL;
    
    public Produto(Connection conexaoMySQL, int id, int codigo, String descricao, float valorVenda, int quantidadeEstoque) {
        this.conexaoMySQL = conexaoMySQL;
        
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    public Produto(Connection conexaoMySQL, int codigo, String descricao, float valorVenda, int quantidadeEstoque) {
        this.conexaoMySQL = conexaoMySQL;
        
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    public Produto(Connection conexaoMySQL,  int codigo, String descricao, float valorVenda) {
        this.conexaoMySQL = conexaoMySQL;
        
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.quantidadeEstoque = 0;
    }
    
    public int getId() {
        return id;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    public final void save() throws SQLException {
        String sql = "INSERT INTO produto (codigo, descricao, valorVenda, quantidadeEstoque) VALUES (?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = conexaoMySQL.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        preparedStatement.setInt(1, codigo);
        preparedStatement.setString(2, descricao);
        preparedStatement.setFloat(3, valorVenda);
        preparedStatement.setInt(4, quantidadeEstoque);
        
        preparedStatement.executeQuery();
        
        int createdId = 0;
        ResultSet rs = preparedStatement.getGeneratedKeys(); 
        if (rs.next()) { 
            createdId = rs.getInt(1);
        } 
        preparedStatement.close();
        
        this.id = createdId;
    }
    
        
    public final void update() throws SQLException {
        String sql = "UPDATE produto SET codigo = ?, descricao = ?, valorVenda = ?, quantidadeEstoque = ? WHERE id = ?";

        PreparedStatement preparedStatement = conexaoMySQL.prepareStatement(sql);
        
        
        preparedStatement.setInt(1, codigo);
        preparedStatement.setString(2, descricao);
        preparedStatement.setFloat(3, valorVenda);
        preparedStatement.setInt(4, quantidadeEstoque);
        preparedStatement.setInt(5, id);
        
        // Alterado para executeUpdate(), pois é um INSERT
        preparedStatement.executeUpdate();
    }
}
