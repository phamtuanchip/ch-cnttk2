package com.cloud.jornal.banco.sv;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cloud.jornal.entidades.News;

public interface NewsAPI {

    

    public News inserir(News noticia) throws SQLException; 

    public void atualizar(News noticia) throws SQLException;  

    public void excluir(int id) throws SQLException; 
    public News buscarPorId(int id) throws SQLException ; 

    public ArrayList<News> buscarTodas() throws SQLException; 
        
    public ArrayList<News> buscarUltimas() throws SQLException ; 

    public ArrayList<News> buscarPorIdSecao(int id) throws SQLException; 

    public ArrayList<News> buscarPorIdJornalista(int id) throws SQLException; 

    public int proxId() throws SQLException; 

}
