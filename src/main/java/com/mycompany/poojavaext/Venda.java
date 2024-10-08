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
import java.util.ArrayList;

/**
 *
 * @author 202302255701
 */
public class Venda {
    private int id;
    private ArrayList<Produto> produtos;
    private Cliente cliente;
    
    private Connection conexaoMySQL;

    public Venda(Connection conexaoMySQL, ArrayList<Produto> produtos, Cliente cliente) {
        this.conexaoMySQL = conexaoMySQL;
        
        this.produtos = produtos;
        this.cliente = cliente;
    }
    
    public Venda(Connection conexaoMySQL, ArrayList<Produto> produtos) {
        this.conexaoMySQL = conexaoMySQL;
        
        this.produtos = produtos;
        
        this.cliente = null;
    }
    
    public Venda(Connection conexaoMySQL, int id, ArrayList<Produto> produtos, Cliente cliente) {
        this.conexaoMySQL = conexaoMySQL;
        this.id = id;
        this.produtos = produtos;
        this.cliente = cliente;
    }
    
    public int getId() {
        return id;
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public final void save() throws SQLException, Exception {
        if (produtos == null) {
            throw new Exception("Sem produtos na venda!");
        }
        
        String sql;
        PreparedStatement preparedStatement;
        
        if (cliente != null) {
            sql = "INSERT INTO venda (cliente) VALUES (?)";

            preparedStatement = conexaoMySQL.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, cliente.getId());
        } else {
            sql = "INSERT INTO venda (cliente) VALUES (NULL)";

            preparedStatement = conexaoMySQL.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }
        
        preparedStatement.executeUpdate();
        
        int createdId = 0;
        ResultSet rs = preparedStatement.getGeneratedKeys(); 
        if (rs.next()) { 
            createdId = rs.getInt(1);
        } 
        preparedStatement.close();
        
        this.id = createdId;
        
        for(Produto p : produtos) { 
            sql = "INSERT INTO venda_produto (produto, venda) VALUES (?, ?)";

            preparedStatement = conexaoMySQL.prepareStatement(sql);
            preparedStatement.setInt(1, p.getId());
            preparedStatement.setInt(2, createdId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
    }
    
    public final void update() throws SQLException {
        String sql;
        PreparedStatement preparedStatement;
        
        if (cliente != null) {
            sql = "INSERT INTO venda SET cliente = ? WHERE id = ?";

            preparedStatement = conexaoMySQL.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, cliente.getId());
            preparedStatement.setInt(1, id);
        } else {
            sql = "INSERT INTO venda (cliente = NULL) WHERE id = ?";

            preparedStatement = conexaoMySQL.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
        }
        
        preparedStatement.executeUpdate();
        
        preparedStatement.close();
    }
}
