/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrarias.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import livrarias.bancoMysql;
import livrarias.classes.editoras;

/**
 *
 * @author lucas.nardi
 */
public class editorasDAO {
    
    public void cadastrar(editoras e){
     
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("INSERT INTO editadoras (nome) VALUES (?)");
            stmt.setString(1, e.getNome());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Editora cadastrada com");
            
        } catch (SQLException ex) {
            
        }
        
    }
    
    public List<editoras> listar() {
        
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<editoras> editoras = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM editadoras");
            rs = stmt.executeQuery();
         
        while (rs.next()){ 
            editoras e = new editoras();
            e.setId(rs.getInt("id_editadoras"));
            e.setNome(rs.getString("nome"));
   
        
            editoras.add(e);
        }   
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return editoras;      
            
    }
    
    public void editar(editoras e){
     
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("UPDATE editadoras set nome = ? where id_editadoras = ? ");
            stmt.setString(1, e.getNome());
            stmt.setInt(2, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Editora cadastrada com sucesso!!");
            
        } catch (SQLException ex) {
            
        }
        
    }
    
    public void excluir(editoras e){
     
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("DELETE FROM editadoras WHERE id_editadoras = ? ");
            stmt.setInt(1, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Editora excluida com sucesso!!");
            
        } catch (SQLException ex) {
            
        }
        
    }
    
     public List<editoras> pesquisar(String texto) {
        
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<editoras> editoras = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM editadoras where nome like ? ");
            stmt.setString(1, "%" + texto + "%");
            rs = stmt.executeQuery();
         
        while (rs.next()){ 
            editoras e = new editoras();
            e.setId(rs.getInt("id_editadoras"));
            e.setNome(rs.getString("nome"));
        
            editoras.add(e);
        }   
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return editoras;      
            
    }
     

    
    
}

