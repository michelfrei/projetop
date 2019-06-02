/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class Manutencao {
     private int id;
    private String nome;
    private int duracao_garantia;

    public Manutencao() {
    }

    public Manutencao(int id, String nome, int duracao_garantia) {
        this.id = id;
        this.nome = nome;
        this.duracao_garantia = duracao_garantia;
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

    public int getDuracao_garantia() {
        return duracao_garantia;
    }

    public void setDuracao_garantia(int duracao_garantia) {
        this.duracao_garantia = duracao_garantia;
    }
    
}
