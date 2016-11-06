package org.nhom8.dttn;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
@Entity
public class BaoNgay extends DauMuc {
private Date date;
private int defaultTIme = 1;
private String type;

public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

public void read() {
	
}

public void write(){
	
}

@Override
public String toString() {
	return "Bao ngay [id=" + getId() + ", name=" + getName() + ", date=" + new SimpleDateFormat("dd-MM-yyyy").format(date) + "]";
}
}
