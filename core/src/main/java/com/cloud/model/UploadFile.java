package com.cloud.model;

import java.io.Serializable;
import java.util.Date;

public class UploadFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8496871879690827677L;
	public UploadFile(Date date, String owner, String filePath, String down_url) {
		day = date;
		this.owner = owner;
		this.setFullPath(filePath);
		this.setDownload_url(down_url);
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getDownload_url() {
		return download_url;
	}
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}
	private Date day;
	private String owner;
	private String fullPath;
	private String download_url;

}
