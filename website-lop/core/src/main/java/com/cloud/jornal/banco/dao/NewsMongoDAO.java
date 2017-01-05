package com.cloud.jornal.banco.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cloud.jornal.banco.sv.NewsAPI;
import com.cloud.jornal.entidades.News;

public class NewsMongoDAO implements NewsAPI{

    @Autowired
    private MongoTemplate mongoTemplate;
     

    public News inserir(News noticia) throws SQLException{
    	if(!getMongoTemplate().collectionExists(News.class))
    	getMongoTemplate().createCollection(News.class);
    	getMongoTemplate().save(noticia);
    	return noticia;
    }

    public void atualizar(News noticia) throws SQLException {
    	// query to search user
    			Query query = new Query(Criteria.where("id").is(noticia.getId()));
    			getMongoTemplate().updateFirst(query, Update.update("name", "test update" + new Date()), News.class);
    }

    public void excluir(int id) throws SQLException {

//        String sql = " delete from jornal.noticia where id = ?;";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//
//        stmt.setInt(1, id);
//
//        stmt.execute();
//        stmt.close();

    }

    public News buscarPorId(int id) throws SQLException{

        String sql = " select * from jornal.noticia " +
                " where id=?;";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        stmt.setLong(1, id);
//        ResultSet rs = stmt.executeQuery();
//
//        News noticiaBusca = null;
//
//        while (rs.next()) {
//            noticiaBusca = new News();
//            
//            noticiaBusca.setId(rs.getInt("id"));
//            noticiaBusca.setTitulo(rs.getString("titulo"));
//            noticiaBusca.setTexto(rs.getString("texto"));
//            noticiaBusca.setSubtitulo(rs.getString("subtitulo"));
//            noticiaBusca.setData(rs.getDate("data_noticia"));
//
//            JornalistDAO daoJornalista = new JornalistDAO();
//            noticiaBusca.setJornalista(daoJornalista.buscarPorId(rs.getInt("id_autor")));
//
//            SecaoDAO daoSecao = new SecaoDAO();
//            noticiaBusca.setSecao(daoSecao.buscarPorId(rs.getInt("id_secao")));
//        }
//
//        rs.close();
//        stmt.close();

        return new News();
    }

    public ArrayList<News> buscarTodas() throws SQLException{

//        String sql = " select * from jornal.noticia; ";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        ResultSet rs = stmt.executeQuery();

        ArrayList<News> noticias = new ArrayList<News>();

//        while (rs.next()) {
//            News noticiaBusca = new News();
//
//            noticiaBusca.setId(rs.getInt("id"));
//            noticiaBusca.setTitulo(rs.getString("titulo"));
//            noticiaBusca.setTexto(rs.getString("texto"));
//            noticiaBusca.setSubtitulo(rs.getString("subtitulo"));
//            noticiaBusca.setData(rs.getDate("data_noticia"));
//
//            JornalistDAO daoJornalista = new JornalistDAO();
//            noticiaBusca.setJornalista(daoJornalista.buscarPorId(rs.getInt("id_autor")));
//
//            SecaoDAO daoSecao = new SecaoDAO();
//            noticiaBusca.setSecao(daoSecao.buscarPorId(rs.getInt("id_secao")));
//
//            noticias.add(noticiaBusca);
//        }
//
//        rs.close();
//        stmt.close();

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

//        String sql = " select * from jornal.noticia where id_secao = ?; ";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        stmt.setInt(1, id);
//        ResultSet rs = stmt.executeQuery();

        ArrayList<News> noticias = new ArrayList<News>();

//        while (rs.next()) {
//            News noticiaBusca = new News();
//
//            noticiaBusca.setId(rs.getInt("id"));
//            noticiaBusca.setTitulo(rs.getString("titulo"));
//            noticiaBusca.setTexto(rs.getString("texto"));
//            noticiaBusca.setSubtitulo(rs.getString("subtitulo"));
//            noticiaBusca.setData(rs.getDate("data_noticia"));
//
//            JornalistDAO daoJornalista = new JornalistDAO();
//            noticiaBusca.setJornalista(daoJornalista.buscarPorId(rs.getInt("id_autor")));
//
//            SecaoDAO daoSecao = new SecaoDAO();
//            noticiaBusca.setSecao(daoSecao.buscarPorId(rs.getInt("id_secao")));
//
//            noticias.add(noticiaBusca);
//        }
//
//        rs.close();
//        stmt.close();

        Collections.sort(noticias);

        return noticias;
    }

    public ArrayList<News> buscarPorIdJornalista(int id) throws SQLException{

//        String sql = " select * from jornal.noticia where id_autor = ?; ";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        stmt.setInt(1, id);
//        ResultSet rs = stmt.executeQuery();

        ArrayList<News> noticias = new ArrayList<News>();

//        while (rs.next()) {
//            News noticiaBusca = new News();
//
//            noticiaBusca.setId(rs.getInt("id"));
//            noticiaBusca.setTitulo(rs.getString("titulo"));
//            noticiaBusca.setTexto(rs.getString("texto"));
//            noticiaBusca.setSubtitulo(rs.getString("subtitulo"));
//            noticiaBusca.setData(rs.getDate("data_noticia"));
//
//            JornalistDAO daoJornalista = new JornalistDAO();
//            noticiaBusca.setJornalista(daoJornalista.buscarPorId(rs.getInt("id_autor")));
//
//            SecaoDAO daoSecao = new SecaoDAO();
//            noticiaBusca.setSecao(daoSecao.buscarPorId(rs.getInt("id_secao")));
//
//            noticias.add(noticiaBusca);
//        }
//
//        rs.close();
//        stmt.close();

        Collections.sort(noticias);

        return noticias;
    }

    public int proxId() throws SQLException{

        int max = 0;
//        String sql = " select max(id) from jornal.noticia; ";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        ResultSet rs = stmt.executeQuery();
//
//        while(rs.next()){
//            max = rs.getInt("max");
//        }
//
//        rs.close();
//        stmt.close();

        return max+1;

    }

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
