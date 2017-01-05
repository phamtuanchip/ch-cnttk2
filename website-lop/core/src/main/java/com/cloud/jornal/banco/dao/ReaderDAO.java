package com.cloud.jornal.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cloud.jornal.banco.conexao.ConectionFactory;
import com.cloud.jornal.entidades.Leitor;

public class ReaderDAO {

    private Connection connection;

    public ReaderDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    public void inserir(Leitor leitor) throws SQLException{

        String sql = " insert into jornal.usuario (id, login, senha, nome, email) values (?, ?, ?, ?, ?); " +
                " insert into jornal.role (id, usuario_id, role) values (?, ?, ?); ";
        PreparedStatement stmt = connection.prepareStatement(sql);

        leitor.setId(this.proxId());

        stmt.setInt(1, leitor.getId());
        stmt.setString(2, leitor.getLogin());
        stmt.setString(3, leitor.getSenha());
        stmt.setString(4, leitor.getNome());
        stmt.setString(5, leitor.getEmail());

        stmt.setInt(6, this.proxIdRole());
        stmt.setInt(7, leitor.getId());
        stmt.setString(8, "Leitor");

        stmt.execute();
        stmt.close();

    }

    public void atualizar(Leitor leitor) throws SQLException {
        String sql = "update jornal.usuario SET nome = ?, login = ?, senha = ?, email = ? where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, leitor.getNome());
        stmt.setString(2, leitor.getLogin());
        stmt.setString(3, leitor.getSenha());
        stmt.setString(4, leitor.getEmail());
        stmt.setInt(5, leitor.getId());

        stmt.execute();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {

        String sql = " delete from jornal.role where usuario_id = ?; " +
                " delete from jornal.usuario where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();

    }

    public Leitor buscarPorId(int id) throws SQLException{

        String sql = "select jornal.usuario.* from jornal.usuario, jornal.role " +
                " where jornal.usuario.id = jornal.role.usuario_id and jornal.role.role = ? and " +
                " jornal.usuario.id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "Leitor");
        stmt.setLong(2, id);
        ResultSet rs = stmt.executeQuery();

        Leitor leitorBusca = null;

        while (rs.next()) {
            leitorBusca = new Leitor();

            leitorBusca.setId(rs.getInt("id"));
            leitorBusca.setNome(rs.getString("nome"));
            leitorBusca.setEmail(rs.getString("email"));
            leitorBusca.setLogin(rs.getString("login"));
            leitorBusca.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();

        return leitorBusca;
    }

    public Leitor buscarPorLogin(String login) throws SQLException{

        String sql = "select jornal.usuario.* from jornal.usuario, jornal.role " +
                " where jornal.usuario.id = jornal.role.usuario_id and jornal.role.role = ? and " +
                " jornal.usuario.login=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "Leitor");
        stmt.setString(2, login);
        ResultSet rs = stmt.executeQuery();

        Leitor leitorBusca = null;

        while (rs.next()) {
            leitorBusca = new Leitor();
            
            leitorBusca.setId(rs.getInt("id"));
            leitorBusca.setNome(rs.getString("nome"));
            leitorBusca.setEmail(rs.getString("email"));
            leitorBusca.setLogin(rs.getString("login"));
            leitorBusca.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();

        return leitorBusca;
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

    private int proxIdRole() throws SQLException{

        int max = 0;
        String sql = " select max(id) from jornal.role; ";
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
