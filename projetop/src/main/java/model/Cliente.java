/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Real
 */
public class Cliente {
    private int id;
    private String nome;
    private int cpf;
    private String descricao;
    private Date saida_concerto;

    public Cliente() {
    }

    public Cliente(int id, String nome, int cpf, String descricao, Date saida_concerto) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.descricao = descricao;
        this.saida_concerto = saida_concerto;
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

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getSaida_concerto() {
        return saida_concerto;
    }

    public void setSaida_concerto(Date saida_concerto) {
        this.saida_concerto = saida_concerto;
    }
}
