package org.nhom8.dttn;

import javax.persistence.Entity;


@Entity
public class Sach  extends DauMuc {
	private String author;
	private String publish;
	private int year;
	private int numOfPage; 
	private int defaultTime = 21;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getNumOfPage() {
		return numOfPage;
	}
	public void setNumOfPage(int numOfPage) {
		this.numOfPage = numOfPage;
	}
	public void read() {	}
	public void write() {	}
	@Override
	public String toString() {
		return "Sach [id=" + getId() + ", name=" + getName() + ", author=" + author + "]";
	}
	 
}
