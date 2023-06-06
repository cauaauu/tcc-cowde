package com.example.tcc_cowde;

public class Usuario {
    private String nome;
    private String nome_usuario;

    public Usuario(String nome, String nome_usuario) {
        this.nome = nome;
        this.nome_usuario = nome_usuario;
    }

    public Usuario(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }
}
