package com.cloud.ws.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloud.jornal.banco.sv.NewsAPI;
import com.cloud.jornal.banco.sv.ServiceFactoryLoader;
import com.cloud.jornal.entidades.News;
import com.cloud.model.Blog;
import com.cloud.model.Notification;
import com.cloud.model.Subject;
import com.cloud.model.UploadFile;

@Path("/rest")
@Component
public class NewsWebservice {
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "/uploads";
	@Autowired
	private ServiceFactoryLoader newsServiceF;



	private  NewsAPI InstanceService(){
		return getNewsServiceF().getService();
	}
 
	//New rest APIs
	
	
	@Path("/news/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getListNews()
	{
		ArrayList<News> list = new ArrayList<News>();
		list.add(new News());
		return  list;
	}
	@Path("/news/search")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> searchNews(@Context News b)
	{
		ArrayList<News> list = new ArrayList<News>();
		//InstanceService().atualizar(b);
		list.add(new News());
		return  list;
	}

	@Path("/news/delete/{id}")
	@DELETE
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public News deleteNews(@PathParam("id") String id)
	{
		return  new News();
	}
	
	@Path("/news/update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Blog updateNews(@Context Blog b)
	{
		return b;
	}
	
	@Path("/news/insert")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public News insertNews(@Context News b)
	{
	
		try {
			return InstanceService().inserir(new News());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new News();
	}
	
	@Path("/news/public/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public News publicNew(@PathParam("id") long id)
	{
	
		return new News();
	}

	public ServiceFactoryLoader getNewsServiceF() {
		return newsServiceF;
	}

	public void setNewsServiceF(ServiceFactoryLoader newsServiceF) {
		this.newsServiceF = newsServiceF;
	}
}