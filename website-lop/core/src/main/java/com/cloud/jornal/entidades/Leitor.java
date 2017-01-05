package com.cloud.jornal.entidades;

public class Leitor extends User {

    @Override
    public boolean equals(Object object){
        if(object instanceof Leitor){
            Leitor l = (Leitor)object;
            if(l.getId() == this.getId() && l.getNome().equals(this.getNome()) && l.getLogin().equals(this.getLogin()) && l.getSenha().equals(this.getSenha()) && l.getEmail().equals(this.getEmail()))
                return true;
        }
        return false;
    }

}
