package com.cloud.jornal.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.cloud.jornal.banco.conexao.ConectionFactory;
import com.cloud.jornal.entidades.Category;
import com.cloud.jornal.util.Util;

public class CategoryDAO {

    private Connection connection;

    public CategoryDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    public void inserir(Category classificado) throws SQLException{

        String sql = " insert into jornal.classificado (id, id_autor, titulo, texto, preco, telefone, melhor_oferta, data_oferta) values (?, ?, ?, ?, ?, ?, ?, "+Util.treatDateToSQLDate(classificado.getDataOferta())+"); ";
        PreparedStatement stmt = connection.prepareStatement(sql);

        classificado.setId(this.proxId());

        stmt.setInt(1, classificado.getId());
        stmt.setInt(2, classificado.getUsuario().getId());
        stmt.setString(3, classificado.getTitulo());
        stmt.setString(4, classificado.getTexto());
        stmt.setDouble(5, classificado.getPreco());
        stmt.setString(6, classificado.getTelefone());
        stmt.setDouble(7, classificado.getMelhorOferta());

        stmt.execute();
        stmt.close();

    }

    public void atualizar(Category classificado) throws SQLException {
        String sql = " update jornal.classificado " +
                " SET id_autor = ?, titulo = ?, texto = ?, preco = ?, telefone = ?, melhor_oferta = ?, data_oferta = "+Util.treatDateToSQLDate(classificado.getDataOferta())+"" +
                " where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, classificado.getUsuario().getId());
        stmt.setString(2, classificado.getTitulo());
        stmt.setString(3, classificado.getTexto());
        stmt.setDouble(4, classificado.getPreco());
        stmt.setString(5, classificado.getTelefone());
        stmt.setDouble(6, classificado.getMelhorOferta());
        stmt.setInt(7, classificado.getId());

        stmt.execute();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {
        String sql = " delete from jornal.classificado where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();
    }

    public Category buscarPorId(int id) throws SQLException{
        String sql = " select * from jornal.classificado " +
                " where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        Category classificadoBusca = null;

        while (rs.next()) {
            classificadoBusca = new Category();
            
            classificadoBusca.setId(rs.getInt("id"));
            classificadoBusca.setTitulo(rs.getString("titulo"));
            classificadoBusca.setTexto(rs.getString("texto"));
            classificadoBusca.setTelefone(rs.getString("telefone"));
            classificadoBusca.setPreco(rs.getDouble("preco"));
            classificadoBusca.setMelhorOferta(rs.getDouble("melhor_oferta"));
            classificadoBusca.setDataOferta(rs.getDate("data_oferta"));

            UsuarioDAO daoUsuario = new UsuarioDAO();
            classificadoBusca.setUsuario(daoUsuario.buscarPorId(rs.getInt("id_autor")));
        }

        rs.close();
        stmt.close();

        return classificadoBusca;
    }

    public ArrayList<Category> buscarTodos() throws SQLException{
        String sql = " select * from jornal.classificado; ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Category> classificados = new ArrayList<Category>();

        while (rs.next()) {
            Category classificadoBusca = new Category();

            classificadoBusca.setId(rs.getInt("id"));
            classificadoBusca.setTitulo(rs.getString("titulo"));
            classificadoBusca.setTexto(rs.getString("texto"));
            classificadoBusca.setTelefone(rs.getString("telefone"));
            classificadoBusca.setPreco(rs.getDouble("preco"));
            classificadoBusca.setMelhorOferta(rs.getDouble("melhor_oferta"));
            classificadoBusca.setDataOferta(rs.getDate("data_oferta"));

            UsuarioDAO daoUsuario = new UsuarioDAO();
            classificadoBusca.setUsuario(daoUsuario.buscarPorId(rs.getInt("id_autor")));

            classificados.add(classificadoBusca);
        }
        rs.close();
        stmt.close();

        Collections.sort(classificados);

        return classificados;
    }

    private int proxId() throws SQLException{
        int max = 0;
        String sql = " select max(id) from jornal.classificado; ";
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
