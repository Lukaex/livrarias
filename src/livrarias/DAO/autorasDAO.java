/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrarias.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import livrarias.bancoMysql;
import livrarias.classes.autoras;
import livrarias.classes.editoras;


/**
 *
 * @author lucas.nardi
 */
public class autorasDAO {
        public void cadastrar(autoras a){
     
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
             stmt = conn.prepareStatement("INSERT INTO autores (nome, endereco,numero, bairro, cidade,cpf) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEndereco());
            stmt.setInt(3, a.getNumero());
            stmt.setString(4, a.getBairro());
            stmt.setString(5, a.getCidade());
            stmt.setBigDecimal(6, new BigDecimal(a.getCpf()));
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Informação cadastrada com sucesso");

            
        } catch (SQLException ex) {
            
        }
        
    }
    public List<autoras> listar() {
        
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<autoras> autoras = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM autores");
            rs = stmt.executeQuery();
         
        while (rs.next()){ 
            autoras a = new autoras();
            a.setId(rs.getInt("id_autor"));
            a.setNome(rs.getString("nome"));
        
            autoras.add(a);
        }   
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return autoras;      
            
    }
}
