package com.cloud.jornal.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cloud.jornal.banco.conexao.ConectionFactory;
import com.cloud.jornal.entidades.User;

public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    public void inserir(User usuario) throws SQLException{

        String sql = " insert into jornal.usuario (id, login, senha, nome, email) values (?, ?, ?, ?, ?); ";
        PreparedStatement stmt = connection.prepareStatement(sql);

        usuario.setId(this.proxId());

        stmt.setInt(1, usuario.getId());
        stmt.setString(2, usuario.getLogin());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getNome());
        stmt.setString(5, usuario.getEmail());

        stmt.execute();
        stmt.close();

    }

    public void atualizar(User usuario) throws SQLException {
        String sql = "update jornal.usuario SET nome = ?, login = ?, senha = ?, email = ? where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getLogin());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getEmail());
        stmt.setInt(5, usuario.getId());

        stmt.execute();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {

        String sql = " delete from jornal.usuario where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();

    }

    public User buscarPorId(int id) throws SQLException{

        String sql = "select * from jornal.usuario " +
                " where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        User usuarioBusca = null;

        while (rs.next()) {
            usuarioBusca = new User();
            
            usuarioBusca.setId(rs.getInt("id"));
            usuarioBusca.setNome(rs.getString("nome"));
            usuarioBusca.setEmail(rs.getString("email"));
            usuarioBusca.setLogin(rs.getString("login"));
            usuarioBusca.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();

        return usuarioBusca;
    }

    public User buscarPorLogin(String login) throws SQLException{

        String sql = "select * from jornal.usuario " +
                " where login = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();

        User usuarioBusca = null;

        while (rs.next()) {
            usuarioBusca = new User();
            
            usuarioBusca.setId(rs.getInt("id"));
            usuarioBusca.setNome(rs.getString("nome"));
            usuarioBusca.setEmail(rs.getString("email"));
            usuarioBusca.setLogin(rs.getString("login"));
            usuarioBusca.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();

        return usuarioBusca;
    }

    private int proxId() throws SQLException{

        int max = 0;
        String sql = " select max(id) from jornal.usuario; ";
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
