/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Real
 */
public class Cliente {
    private int id;
    private String nome;
    private int cpf;
    private String descricao;
    private LocalDate saida_concerto;
    private LocalDate garantia;
    //private LocalDate detea = LocalDate.now();
    
    public Cliente() {
    }
    
    /*public Cliente(LocalDate detea){
        this.detea = detea;
    }
    public LocalDate getDetea() {
        return detea;
    }

    public void setDetea(LocalDate detea) {
        this.detea = detea;
    }*/

    public Cliente(int id, String nome, int cpf, String descricao, LocalDate saida_concerto, LocalDate garantia) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.descricao = descricao;
        this.saida_concerto = saida_concerto;
        this.garantia = garantia;
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

    public LocalDate getSaida_concerto() {
        return saida_concerto;
    }

    public void setSaida_concerto(LocalDate saida_concerto) {
        this.saida_concerto = saida_concerto;
    }
    
    public LocalDate getGarantia() {
        return garantia;
    }

    public void setGarantia(LocalDate garantia) {
        this.garantia = garantia;
    }
       
}
