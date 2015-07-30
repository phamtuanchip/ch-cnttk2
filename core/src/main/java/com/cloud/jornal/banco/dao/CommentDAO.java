package com.cloud.jornal.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cloud.jornal.banco.conexao.ConectionFactory;
import com.cloud.jornal.entidades.Comment;

public class CommentDAO {

    private Connection connection;

    public CommentDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    public void inserir(Comment comentario) throws SQLException {

        String sql = " insert into jornal.comentario (id, id_autor, id_noticia, texto) values (?, ?, ?, ?); ";
        PreparedStatement stmt = connection.prepareStatement(sql);

        comentario.setId(this.proxId());

        stmt.setInt(1, comentario.getId());
        stmt.setInt(2, comentario.getLeitor().getId());
        stmt.setInt(3, comentario.getNoticia().getId());
        stmt.setString(4, comentario.getTexto());

        stmt.execute();
        stmt.close();

    }

    public void atualizar(Comment comentario) throws SQLException {
        String sql = " update jornal.comentario " +
                " SET id_autor = ?, id_noticia = ?, texto = ? " +
                " where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, comentario.getLeitor().getId());
        stmt.setInt(2, comentario.getNoticia().getId());
        stmt.setString(3, comentario.getTexto());
        stmt.setInt(4, comentario.getId());

        stmt.execute();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {

        String sql = " delete from jornal.comentario where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();

    }

    public Comment buscarPorId(int id) throws SQLException {

        String sql = " select * from jornal.comentario " +
                " where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        Comment comentarioBusca = null;

        while (rs.next()) {
            comentarioBusca = new Comment();

            comentarioBusca.setId(rs.getInt("id"));
            comentarioBusca.setTexto(rs.getString("texto"));

            ReaderDAO daoLeitor = new ReaderDAO();
            comentarioBusca.setLeitor(daoLeitor.buscarPorId(rs.getInt("id_autor")));

            NewsDAO daoNoticia = new NewsDAO();
            comentarioBusca.setNoticia(daoNoticia.buscarPorId(rs.getInt("id_noticia")));
        }

        rs.close();
        stmt.close();

        return comentarioBusca;
    }

    public ArrayList<Comment> buscarTodos() throws SQLException {

        String sql = " select * from jornal.comentario; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Comment> comentarios = new ArrayList<Comment>();

        while (rs.next()) {
            Comment comentarioBusca = new Comment();

            comentarioBusca.setId(rs.getInt("id"));
            comentarioBusca.setTexto(rs.getString("texto"));

            ReaderDAO daoLeitor = new ReaderDAO();
            comentarioBusca.setLeitor(daoLeitor.buscarPorId(rs.getInt("id_autor")));

            NewsDAO daoNoticia = new NewsDAO();
            comentarioBusca.setNoticia(daoNoticia.buscarPorId(rs.getInt("id_noticia")));

            comentarios.add(comentarioBusca);
        }

        rs.close();
        stmt.close();

        return comentarios;
    }

    public ArrayList<Comment> buscarPorIdNoticia(int id) throws SQLException {

        String sql = " select * from jornal.comentario where id_noticia = ?; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Comment> comentarios = new ArrayList<Comment>();

        while (rs.next()) {
            Comment comentarioBusca = new Comment();

            comentarioBusca.setId(rs.getInt("id"));
            comentarioBusca.setTexto(rs.getString("texto"));

            ReaderDAO daoLeitor = new ReaderDAO();
            comentarioBusca.setLeitor(daoLeitor.buscarPorId(rs.getInt("id_autor")));

            NewsDAO daoNoticia = new NewsDAO();
            comentarioBusca.setNoticia(daoNoticia.buscarPorId(rs.getInt("id_noticia")));

            comentarios.add(comentarioBusca);
        }

        rs.close();
        stmt.close();

        return comentarios;
    }

    private int proxId() throws SQLException {

        int max = 0;
        String sql = " select max(id) from jornal.comentario; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            max = rs.getInt("max");
        }

        rs.close();
        stmt.close();

        return max + 1;

    }
}
