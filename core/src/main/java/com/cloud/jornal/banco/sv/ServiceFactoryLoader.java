package com.cloud.jornal.banco.sv;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceFactoryLoader implements ServiceFactory {
	@Autowired
	private NewsAPI newsService;

	@Override
	public NewsAPI getService() {
		// TODO Auto-generated method stub
		return newsService;
	}

	public NewsAPI getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsAPI newService) {
		this.newsService = newService;
	}
	
	 
 
 
}
