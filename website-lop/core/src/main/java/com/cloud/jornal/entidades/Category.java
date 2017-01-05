package com.cloud.jornal.entidades;

import java.util.Date;

public class Category implements Comparable<Category> {

    private int id;
    private User usuario;
    private String titulo;
    private String texto;
    private double preco;
    private String telefone;
    private double melhorOferta;
    private Date dataOferta;

    public Category() {
    }

    public Date getDataOferta() {
        return dataOferta;
    }

    public void setDataOferta(Date dataOferta) {
        this.dataOferta = dataOferta;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMelhorOferta() {
        return melhorOferta;
    }

    public void setMelhorOferta(double melhorOferta) {
        this.melhorOferta = melhorOferta;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int compareTo(Category arg0) {
        return arg0.getDataOferta().compareTo(this.getDataOferta());
    }

}
