package com.example.biblioteca;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Livro {
    String nome, genero, descricao;
    String paginas;

    public Livro(String nome, String genero, String paginas, String descricao) {
        this.nome = nome;
        this.genero = genero;
        this.paginas = paginas;
        this.descricao = descricao;
    }
    public Livro(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void salvar() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Livros").child(nome).setValue(this);
    }
}

