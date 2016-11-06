package org.nhom8.dttn;

import java.util.Arrays;

import org.nhom8.csdl.ObjectID;

public class DauMuc {
	private String name;
	private int num;
	private String isbn;
	private ObjectID[] items;
	private ObjectID[] resevarions;
	
	public void addItem(ObjectID item) {
		Arrays.asList(items).add(item);
		items = (ObjectID[]) Arrays.asList(items).toArray();
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public ObjectID[] getItems() {
		return items;
	}
	public void setItems(ObjectID[] items) {
		this.items = items;
	}
	public PhieuDatMuon getResevarions() {
		return null;
	}
	public void setResevarions(PhieuDatMuon[] resevarions) {
		this.resevarions = resevarions;
	}
	 
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	public DauMuc findOnName() {
		return this;
	}
	public DauMuc finOnISBN() {
		return this;
	}
	public DauMuc getTitle(){
		return this;
	}
	public String getISBN(){
		return isbn;
	}
	
	public void timKiem(){
		
	}
    public void  taoLap(){
    	
    }
    public void loaiBo(){
    	
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	public void read() {
		
	 }
	 public void write() {
		 
	 }
}
