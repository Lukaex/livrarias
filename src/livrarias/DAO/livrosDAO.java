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
import javax.swing.table.DefaultTableModel;
import livrarias.bancoMysql;
import livrarias.classes.autoras;
import livrarias.classes.editoras;
import livrarias.classes.livros;

/**
 *
 * @author lucas.nardi
 */
public class livrosDAO {

    public void cadastrar(livros l) {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO livros (id_editora, id_autor, titulo, ano) VALUES (?,?,?,?)");
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Informação cadastrada com sucesso");

        } catch (SQLException ex) {

        }

    }

    public List<livros> listar() {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<livros> livros = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("select l.id_livros, l.id_editora, l.id_livros, e.nome as nome_editora, a.nome as nome_autor, l.titulo, l.ano from livros as l\n"
                    + "inner join editadoras as e on (e.id_editadoras = l.id_editora)\n"
                    + "inner join autores as a on (a.id_autor = l.id_autor)");
            rs = stmt.executeQuery();

            while (rs.next()) {
                livros l = new livros();
                l.setId_livro(rs.getInt("id_livros"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));

                editoras e = new editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditora(e);

                autoras a = new autoras();
                a.setNome(rs.getString("nome_autor"));
                l.setAutor(a);

                livros.add(l);

            }

        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livros;

    }

    public void editar(livros l) {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE livros set id_editora = ?, id_autor = ?, titulo = ?, ano = ? where id_livros = ? ");
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());
            stmt.setInt(5, l.getId_livro());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(livrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Editora editada com sucesso!!");

    }

    public void excluir(livros l) {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM livros WHERE id_livros = ? ");
            stmt.setInt(1, l.getId_livro());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Editora excluida com sucesso!!");

        } catch (SQLException ex) {

        }

    }

    public List<livros> pesquisar(String texto) {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<livros> livros = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("select l.id_livros, l.id_editora, l.id_livros, e.nome as nome_editora, a.nome as nome_autor, l.titulo, l.ano from livros as l\n"
                    + "inner join editadoras as e on (e.id_editadoras = l.id_editora)\n"
                    + "inner join autores as a on (a.id_autor = l.id_autor) where titulo like ? ");
            stmt.setString(1, "%" + texto + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                livros l = new livros();
                l.setId_livro(rs.getInt("id_livros"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));

                editoras e = new editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditora(e);

                autoras a = new autoras();
                a.setNome(rs.getString("nome_autor"));
                l.setAutor(a);

                livros.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(livrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livros;

    }
}
