package com.cloud.jornal.entidades;

import java.util.ArrayList;
import java.util.Date;

public class News implements Comparable<News> {

    private int id;
    private Secao secao;
    private Jornalist jornalista;
    private String titulo;
    private String subtitulo;
    private String texto;
    private Date data;
    private ArrayList<Comment> comentarios;

    public News() {
        this.comentarios = new ArrayList<Comment>();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jornalist getJornalista() {
        return jornalista;
    }

    public void setJornalista(Jornalist jornalista) {
        this.jornalista = jornalista;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
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

    public ArrayList<Comment> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comment> comentarios) {
        this.comentarios = comentarios;
    }

    public Comment getComentario(int id){
        for(int i = 0; i<comentarios.size(); i++){
            if(comentarios.get(i).getId() == id)
                return comentarios.get(i);
        }
        return null;
    }

    public Comment removerComentario(int id){
        for(int i = 0; i<comentarios.size(); i++){
            if(comentarios.get(i).getId() == id)
                return comentarios.remove(i);
        }
        return null;
    }

    public int compareTo(News arg0) {
        return arg0.getData().compareTo(this.getData());
    }

}
