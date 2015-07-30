package com.cloud.jornal.entidades;

public class Comment {

    private int id;
    private Leitor leitor;
    private News noticia;
    private String texto;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public News getNoticia() {
        return noticia;
    }

    public void setNoticia(News noticia) {
        this.noticia = noticia;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
