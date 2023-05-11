package br.pro.apppetshop;

import androidx.annotation.NonNull;

public class Cachorro {
    private int id;
    private String nome,idade;

    public Cachorro(){
    }

     public Cachorro(int id, String nome, String idade){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
     }

    @NonNull
    @Override
    public String toString() {
        return nome + " - IDADE: " + idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setRa(String idade) {
        this.idade = idade;
    }

    public void setIdade(String string) {
    }
}
