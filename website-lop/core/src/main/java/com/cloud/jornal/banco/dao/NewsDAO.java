package com.cloud.jornal.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.cloud.jornal.banco.conexao.ConectionFactory;
import com.cloud.jornal.banco.sv.NewsAPI;
import com.cloud.jornal.entidades.News;
import com.cloud.jornal.util.Util;

public class NewsDAO implements NewsAPI  {

    private Connection connection;

    public NewsDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    public News inserir(News noticia) throws SQLException{

        String sql = " insert into jornal.noticia (id, id_secao, id_autor, titulo, subtitulo, texto, data_noticia) values (?, ?, ?, ?, ?, ?, "+Util.treatDateToSQLDate(noticia.getData())+"); ";
        PreparedStatement stmt = connection.prepareStatement(sql);

        noticia.setId(this.proxId());

        stmt.setInt(1, noticia.getId());
        stmt.setInt(2, noticia.getSecao().getId());
        stmt.setInt(3, noticia.getJornalista().getId());
        stmt.setString(4, noticia.getTitulo());
        stmt.setString(5, noticia.getSubtitulo());
        stmt.setString(6, noticia.getTexto());

        stmt.execute();
        stmt.close();
        return noticia;

    }

    public void atualizar(News noticia) throws SQLException {
        String sql = " update jornal.noticia " +
                " SET id_secao = ?, id_autor = ?, titulo = ?, subtitulo = ?, texto = ?, data_noticia = "+Util.treatDateToSQLDate(noticia.getData())+" " +
                " where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, noticia.getSecao().getId());
        stmt.setInt(2, noticia.getJornalista().getId());
        stmt.setString(3, noticia.getTitulo());
        stmt.setString(4, noticia.getSubtitulo());
        stmt.setString(5, noticia.getTexto());
        stmt.setInt(6, noticia.getId());

        stmt.execute();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {

        String sql = " delete from jornal.noticia where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();

    }

    public News buscarPorId(int id) throws SQLException{

        String sql = " select * from jornal.noticia " +
                " where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        News noticiaBusca = null;

        while (rs.next()) {
            noticiaBusca = new News();
            
            noticiaBusca.setId(rs.getInt("id"));
            noticiaBusca.setTitulo(rs.getString("titulo"));
            noticiaBusca.setTexto(rs.getString("texto"));
            noticiaBusca.setSubtitulo(rs.getString("subtitulo"));
            noticiaBusca.setData(rs.getDate("data_noticia"));

            JornalistDAO daoJornalista = new JornalistDAO();
            noticiaBusca.setJornalista(daoJornalista.buscarPorId(rs.getInt("id_autor")));

            SecaoDAO daoSecao = new SecaoDAO();
            noticiaBusca.setSecao(daoSecao.buscarPorId(rs.getInt("id_secao")));
        }

        rs.close();
        stmt.close();

        return noticiaBusca;
    }

    public ArrayList<News> buscarTodas() throws SQLException{

        String sql = " select * from jornal.noticia; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<News> noticias = new ArrayList<News>();

        while (rs.next()) {
            News noticiaBusca = new News();

            noticiaBusca.setId(rs.getInt("id"));
            noticiaBusca.setTitulo(rs.getString("titulo"));
            noticiaBusca.setTexto(rs.getString("texto"));
            noticiaBusca.setSubtitulo(rs.getString("subtitulo"));
            noticiaBusca.setData(rs.getDate("data_noticia"));

            JornalistDAO daoJornalista = new JornalistDAO();
            noticiaBusca.setJornalista(daoJornalista.buscarPorId(rs.getInt("id_autor")));

            SecaoDAO daoSecao = new SecaoDAO();
            noticiaBusca.setSecao(daoSecao.buscarPorId(rs.getInt("id_secao")));

            noticias.add(noticiaBusca);
        }

        rs.close();
        stmt.close();

        Collections.sort(noticias);

        return noticias;
    }

    public ArrayList<News> buscarUltimas() throws SQLException{

        ArrayList<News> busca = this.buscarTodas();
        ArrayList<News> noticias = new ArrayList<News>(10);
        int cont = 0;

        for(int i=0; i < busca.size(); i++){
            if(cont < 10)
                noticias.add(busca.get(i));
            else
                break;
            cont++;
        }

        return noticias;
    }

    public ArrayList<News> buscarPorIdSecao(int id) throws SQLException{

        String sql = " select * from jornal.noticia where id_secao = ?; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        ArrayList<News> noticias = new ArrayList<News>();

        while (rs.next()) {
            News noticiaBusca = new News();

            noticiaBusca.setId(rs.getInt("id"));
            noticiaBusca.setTitulo(rs.getString("titulo"));
            noticiaBusca.setTexto(rs.getString("texto"));
            noticiaBusca.setSubtitulo(rs.getString("subtitulo"));
            noticiaBusca.setData(rs.getDate("data_noticia"));

            JornalistDAO daoJornalista = new JornalistDAO();
            noticiaBusca.setJornalista(daoJornalista.buscarPorId(rs.getInt("id_autor")));

            SecaoDAO daoSecao = new SecaoDAO();
            noticiaBusca.setSecao(daoSecao.buscarPorId(rs.getInt("id_secao")));

            noticias.add(noticiaBusca);
        }

        rs.close();
        stmt.close();

        Collections.sort(noticias);

        return noticias;
    }

    public ArrayList<News> buscarPorIdJornalista(int id) throws SQLException{

        String sql = " select * from jornal.noticia where id_autor = ?; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        ArrayList<News> noticias = new ArrayList<News>();

        while (rs.next()) {
            News noticiaBusca = new News();

            noticiaBusca.setId(rs.getInt("id"));
            noticiaBusca.setTitulo(rs.getString("titulo"));
            noticiaBusca.setTexto(rs.getString("texto"));
            noticiaBusca.setSubtitulo(rs.getString("subtitulo"));
            noticiaBusca.setData(rs.getDate("data_noticia"));

            JornalistDAO daoJornalista = new JornalistDAO();
            noticiaBusca.setJornalista(daoJornalista.buscarPorId(rs.getInt("id_autor")));

            SecaoDAO daoSecao = new SecaoDAO();
            noticiaBusca.setSecao(daoSecao.buscarPorId(rs.getInt("id_secao")));

            noticias.add(noticiaBusca);
        }

        rs.close();
        stmt.close();

        Collections.sort(noticias);

        return noticias;
    }

    public int proxId() throws SQLException{

        int max = 0;
        String sql = " select max(id) from jornal.noticia; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            max = rs.getInt("max");
        }

        rs.close();
        stmt.close();

        return max+1;

    }

}
