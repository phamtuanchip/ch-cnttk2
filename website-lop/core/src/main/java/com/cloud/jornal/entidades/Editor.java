package com.cloud.jornal.entidades;

import java.util.ArrayList;

public class Editor extends User {

    private ArrayList<Jornalist> jornalistas;
    private ArrayList<Secao> secoes;
    private ArrayList<Category> classificados;

    public Editor() {
        this.jornalistas = new ArrayList<Jornalist>();
        this.secoes = new ArrayList<Secao>();
        this.classificados = new ArrayList<Category>();
    }

    public ArrayList<Category> getClassificados() {
        return classificados;
    }

    public void setClassificados(ArrayList<Category> classificados) {
        this.classificados = classificados;
    }

    public ArrayList<Jornalist> getJornalistas() {
        return jornalistas;
    }

    public void setJornalistas(ArrayList<Jornalist> jornalistas) {
        this.jornalistas = jornalistas;
    }

    public ArrayList<Secao> getSecoes() {
        return secoes;
    }

    public void setSecoes(ArrayList<Secao> secoes) {
        this.secoes = secoes;
    }

    public Jornalist getJornalista(int id){
        for(int i=0; i< jornalistas.size(); i++){
            if(jornalistas.get(i).getId() == id)
                return jornalistas.get(i);
        }
        return null;
    }

    public Secao getSecao(int id){
        for(int i=0; i< secoes.size(); i++){
            if(secoes.get(i).getId() == id)
                return secoes.get(i);
        }
        return null;
    }

    public Category getClassificado(int id){
        for(int i=0; i< classificados.size(); i++){
            if(classificados.get(i).getId() == id)
                return classificados.get(i);
        }
        return null;
    }

    public Jornalist removerJornalista(int id){
        for(int i=0; i< jornalistas.size(); i++){
            if(jornalistas.get(i).getId() == id)
                return jornalistas.remove(i);
        }
        return null;
    }

    public Secao removerSecao(int id){
        for(int i=0; i< secoes.size(); i++){
            if(secoes.get(i).getId() == id)
                return secoes.remove(i);
        }
        return null;
    }

    public Category removerClassificado(int id){
        for(int i=0; i< classificados.size(); i++){
            if(classificados.get(i).getId() == id)
                return classificados.remove(i);
        }
        return null;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Editor){
            Editor e = (Editor)object;
            if(e.getId() == this.getId() && e.getNome().equals(this.getNome()) && e.getLogin().equals(this.getLogin()) && e.getSenha().equals(this.getSenha()) && e.getEmail().equals(this.getEmail()))
                return true;
        }
        return false;
    }

}
