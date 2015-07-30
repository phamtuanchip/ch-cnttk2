package com.cloud.jornal.entidades;

public class User extends Person {

    private String login;
    private String senha;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof User){
            User u = (User)object;
            if(u.getId() == this.getId() && u.getNome().equals(this.getNome()) && u.getLogin().equals(this.getLogin()) && u.getSenha().equals(this.getSenha()) && u.getEmail().equals(this.getEmail()))
                return true;
        }
        return false;
    }

}
