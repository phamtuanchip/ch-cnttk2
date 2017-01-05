package com.cloud.jornal.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.cloud.jornal.banco.conexao.ConectionFactory;
import com.cloud.jornal.entidades.Secao;

public class SecaoDAO {
    
    private Connection connection;

    public SecaoDAO() throws SQLException {
        connection = ConectionFactory.getConexao();
    }

    /**
     * Insere uma secao no banco de dados.
     * @param secao
     * @throws java.sql.SQLException
     */
    public void inserir(Secao secao) throws SQLException{

        String sql = "insert into jornal.secao (id, titulo, descricao) values (?, ?, ?);";
        PreparedStatement stmt = connection.prepareStatement(sql);

        secao.setId(this.proxId());

        stmt.setInt(1, secao.getId());
        stmt.setString(2, secao.getTitulo());
        stmt.setString(3, secao.getDescricao());

        stmt.execute();
        stmt.close();

    }

    /**
     * Atualiza uma secao já existente no banco de dados.
     * @param secao
     * @throws java.sql.SQLException
     */
    public void atualizar(Secao secao) throws SQLException {
        String sql = "update jornal.secao SET titulo = ?, descricao = ? where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, secao.getTitulo());
        stmt.setString(2, secao.getDescricao());
        stmt.setInt(3, secao.getId());

        stmt.execute();
        stmt.close();
    }

    /**
     * Deleta uma secao do banco de dados. Utiliza o id para identificar
     * qual secao será deletada.
     * @param secao
     * @throws java.sql.SQLException
     */
    public void excluir(int id) throws SQLException {

        String sql = "delete from jornal.secao where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.execute();
        stmt.close();

    }

    /**
     * Busca uma secao no banco de dados. Utiliza o id para saber qual secao
     * será recuperada.
     * @param secao
     * @return Secao
     * @throws java.sql.SQLException
     */
    public Secao buscarPorId(int id) throws SQLException{

        String sql = "select * from jornal.secao where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        Secao secaoBusca = null;
        
        while (rs.next()) {
            secaoBusca = new Secao();

            secaoBusca.setId(rs.getInt("id"));
            secaoBusca.setDescricao(rs.getString("descricao"));
            secaoBusca.setTitulo(rs.getString("titulo"));
        }

        rs.close();
        stmt.close();

        return secaoBusca;
    }

    /**
     * Busca todas as secoes existentes no banco de dados.
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Secao> buscarTodos() throws SQLException{
        String sql = "select * from jornal.secao;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Secao> secoes = new ArrayList<Secao>();

        while (rs.next()) {
            Secao secao = new Secao();
            secao.setId(rs.getInt("id"));
            secao.setDescricao(rs.getString("descricao"));
            secao.setTitulo(rs.getString("titulo"));

            secoes.add(secao);
        }

        rs.close();
        stmt.close();

        Collections.sort(secoes);

        return secoes;
    }



    private int proxId() throws SQLException{

        int max = 0;
        String sql = " select max(id) from jornal.secao; ";
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
