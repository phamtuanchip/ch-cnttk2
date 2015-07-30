package com.cloud.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "tasks")
public class Task implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2733976798890186613L;
	@Id
	private   long id;
	private   String name;
	private   int pri;
	private   Date date;
	private   boolean remide;
	private   String add;
	private   String lat;
	private   String lgn;
	private   boolean remided;
	public Task(){
		
	}
	public Task(Date d){
		this.date = d;
	}
	public Task(long id, String name, Date d, boolean hasRemind){
		this.id = id;
		this.name = name ;
		this.date = d;
		this.remide = hasRemind;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPri() {
		return pri;
	}
	public void setPri(int pri) {
		this.pri = pri;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public boolean isRemide() {
		return remide;
	}
	public void setRemide(boolean remide) {
		this.remide = remide;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLgn() {
		return lgn;
	}
	public void setLgn(String lgn) {
		this.lgn = lgn;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public boolean isRemided() {
		return remided;
	}
	public void setRemided(boolean remided) {
		this.remided = remided;
	}
	
 
	
}
