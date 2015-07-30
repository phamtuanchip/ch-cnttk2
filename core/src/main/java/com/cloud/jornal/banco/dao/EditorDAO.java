package com.cloud.jornal.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cloud.jornal.banco.conexao.ConectionFactory;
import com.cloud.jornal.entidades.Editor;

public class EditorDAO {

    private Connection connection;

    public EditorDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    public void inserir(Editor editor) throws SQLException{

        String sql = " insert into jornal.usuario (id, login, senha, nome, email) values (?, ?, ?, ?, ?); " +
                " insert into jornal.role (id, usuario_id, role) values (?, ?, ?); ";
        PreparedStatement stmt = connection.prepareStatement(sql);

        editor.setId(this.proxId());

        stmt.setInt(1, editor.getId());
        stmt.setString(2, editor.getLogin());
        stmt.setString(3, editor.getSenha());
        stmt.setString(4, editor.getNome());
        stmt.setString(5, editor.getEmail());

        stmt.setInt(6, this.proxIdRole());
        stmt.setInt(7, editor.getId());
        stmt.setString(8, "Editor");

        stmt.execute();
        stmt.close();

    }

    public void atualizar(Editor editor) throws SQLException {
        String sql = "update jornal.usuario SET nome = ?, login = ?, senha = ?, email = ? where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, editor.getNome());
        stmt.setString(2, editor.getLogin());
        stmt.setString(3, editor.getSenha());
        stmt.setString(4, editor.getEmail());
        stmt.setInt(5, editor.getId());

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

    public Editor buscarPorId(int id) throws SQLException{

        String sql = "select jornal.usuario.* from jornal.usuario, jornal.role " +
                " where jornal.usuario.id = jornal.role.usuario_id and jornal.role.role = ? and " +
                " jornal.usuario.id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "Editor");
        stmt.setLong(2, id);
        ResultSet rs = stmt.executeQuery();

        Editor editorBusca = null;

        while (rs.next()) {
            editorBusca = new Editor();
            
            editorBusca.setId(rs.getInt("id"));
            editorBusca.setNome(rs.getString("nome"));
            editorBusca.setEmail(rs.getString("email"));
            editorBusca.setLogin(rs.getString("login"));
            editorBusca.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();

        return editorBusca;
    }

    public Editor buscarPorLogin(String login) throws SQLException{

        String sql = "select jornal.usuario.* from jornal.usuario, jornal.role " +
                " where jornal.usuario.id = jornal.role.usuario_id and jornal.role.role = ? and " +
                " jornal.usuario.login=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "Editor");
        stmt.setString(2, login);
        ResultSet rs = stmt.executeQuery();

        Editor editorBusca = null;

        while (rs.next()) {
            editorBusca = new Editor();
            
            editorBusca.setId(rs.getInt("id"));
            editorBusca.setNome(rs.getString("nome"));
            editorBusca.setEmail(rs.getString("email"));
            editorBusca.setLogin(rs.getString("login"));
            editorBusca.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();

        return editorBusca;
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
