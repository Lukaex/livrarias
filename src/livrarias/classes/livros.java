/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrarias.classes;

import java.sql.Date;

/**
 *
 * @author lucas.nardi
 */
public class livros {
    private int id_livro;
    private String titulo;
    private int ano;
    private editoras editora;
    private autoras autor;

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public editoras getEditora() {
        return editora;
    }

    public void setEditora(editoras editora) {
        this.editora = editora;
    }

    public autoras getAutor() {
        return autor;
    }

    public void setAutor(autoras autor) {
        this.autor = autor;
    }


}
