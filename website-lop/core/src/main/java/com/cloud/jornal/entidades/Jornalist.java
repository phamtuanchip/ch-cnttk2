package com.cloud.jornal.entidades;

import java.util.ArrayList;

public class Jornalist extends User {

    private ArrayList<News> noticias;
    private ArrayList<Secao> secoes;

    public Jornalist() {
        this.noticias = new ArrayList<News>();
        this.secoes = new ArrayList<Secao>();
    }

    public ArrayList<News> getNoticias() {
        return noticias;
    }

    public void setNoticias(ArrayList<News> noticias) {
        this.noticias = noticias;
    }

    public ArrayList<Secao> getSecoes() {
        return secoes;
    }

    public void setSecoes(ArrayList<Secao> secoes) {
        this.secoes = secoes;
    }

    public News getNoticia(int id){
        for(int i = 0; i < noticias.size(); i++){
            if(noticias.get(i).getId() == id)
                return noticias.get(i);
        }
        return null;
    }

    public Secao getSecao(int id){
        for(int i = 0; i < secoes.size(); i++){
            if(secoes.get(i).getId() == id)
                return secoes.get(i);
        }
        return null;
    }

    public News removerNoticia(int id){
        for(int i = 0; i < noticias.size(); i++){
            if(noticias.get(i).getId() == id)
                return noticias.remove(i);
        }
        return null;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Jornalist){
            Jornalist j = (Jornalist)object;
            if(j.getId() == this.getId() && j.getNome().equals(this.getNome()) && j.getLogin().equals(this.getLogin()) && j.getSenha().equals(this.getSenha()) && j.getEmail().equals(this.getEmail()))
                return true;
        }
        return false;
    }
    
}
