package org.nhom8.dttn;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class TapChi extends DauMuc {
private int volum;
private Date timeOut;
private int defaultTime = 7;
private int year;
public int getVolum() {
	return volum;
}
public void setVolum(int volum) {
	this.volum = volum;
}
public Date getTimeOut() {
	return timeOut;
}
public void setTimeOut(Date timeOut) {
	this.timeOut = timeOut;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
@Override
public String toString() {
	return "Tapchi [id=" + getId() + ", name=" + getName() + ", year=" + year + "]";
}
}
