/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.poojavaext;

import com.mycompany.poojavaext.frames.CadClientesForm;
import com.mycompany.poojavaext.frames.VendasForm;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 202302255701
 */
public class PooJavaExt extends javax.swing.JFrame {

    private final ConexaoMySQL conexaoMySQL;

    private ArrayList<Produto> produtos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Venda> vendas;
    private JPanel currentPanel;

    /**
     * Creates new form PrincipalFrame
     */
    public PooJavaExt() {
        initComponents();

        conexaoMySQL = new ConexaoMySQL();

        Connection conexao = conexaoMySQL.getConexaoMySQL();

        // Carrega produtos
        this.produtos = new ArrayList<>();
        try {
            String sql = "SELECT id, codigo, descricao, valorVenda, quantidadeEstoque FROM produto";

            PreparedStatement preparedStatement;
            preparedStatement = conexao.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Produto p = new Produto(conexao,
                        rs.getInt("id"),
                        rs.getInt("codigo"),
                        rs.getString("descricao"),
                        rs.getFloat("valorvenda"),
                        rs.getInt("QuantidadeEstoque"));

                produtos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao carregar produtos");
        }

        // Carrega clientes
        this.clientes = new ArrayList<>();
        try {
            String sql = "SELECT id, cpf, nome, email, dataNascimento FROM cliente";

            PreparedStatement preparedStatement;
            preparedStatement = conexao.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(conexao,
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email"));

                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao carregar produtos");
        }
        
        // Carrega vendas
        this.vendas = new ArrayList<>();
        try {
            String sql = "SELECT id, cliente FROM venda";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idDaVenda = rs.getInt("id");
                ArrayList<Produto> produtosNaVenda = new ArrayList<>();
                
                sql = "SELECT id, produto FROM venda_produto WHERE venda = ?";
                
                preparedStatement = conexao.prepareStatement(sql);
                preparedStatement.setInt(1, idDaVenda);
                
                ResultSet rsProdutosVenda = preparedStatement.executeQuery();
                
                while(rsProdutosVenda.next()) {
                    for(Produto p : produtos) {
                        if (p.getId() == rsProdutosVenda.getInt("produto")) {
                            produtosNaVenda.add(p);
                            break;
                        }
                    }
                }
                
                Cliente clienteDaVenda = null;
                int clienteVenda = rs.getInt("cliente");
                if (clienteVenda != 0) {
                    for(Cliente c : clientes) {
                        if (c.getId() == clienteVenda) {
                            clienteDaVenda = c;
                            break;
                        }
                    }
                }
                
                Venda v = new Venda(conexao,
                        idDaVenda,
                        produtosNaVenda,
                        clienteDaVenda);

                vendas.add(v);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao carregar vendas");
        }
    }

    public void adicionarProduto(Produto produto) {
        try {
            produto.save();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar!");
        }
        produtos.add(produto);
    }

    public void adicionarVenda(Venda venda) {
        try {
            venda.save();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        vendas.add(venda);
    }

    public void adicionarCliente(Cliente cliente) {
        try {
            cliente.save();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar!");
            System.out.println(ex);
        }
        clientes.add(cliente);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema");

        jButton1.setText("Realizar Venda");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton2.setText("Cadastro de Clientes");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Cadastro de Produtos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1198, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        jButton4.setText("Consultar Vendas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // Botão realizar venda
        currentPanel = new VendasForm(conexaoMySQL.getConexaoMySQL(), this, produtos);
        jPanel1.removeAll();
        currentPanel.setSize(jPanel1.getSize());
        jPanel1.add(currentPanel);
        jPanel1.revalidate();
        jPanel1.repaint();
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // Botão realizar venda
        currentPanel = new CadClientesForm(conexaoMySQL.getConexaoMySQL(), this, clientes);
        jPanel1.removeAll();
        currentPanel.setSize(jPanel1.getSize());
        jPanel1.add(currentPanel);
        jPanel1.revalidate();
        jPanel1.repaint();
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PooJavaExt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PooJavaExt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PooJavaExt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PooJavaExt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PooJavaExt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
