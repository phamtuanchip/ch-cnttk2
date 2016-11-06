package org.nhom8.dttn;

import java.util.Date;

import org.nhom8.csdl.ObjectID;
import org.nhom8.csdl.Persistent;

public class PhieuDatMuon extends Persistent implements ObjectID {
 private Date resevDate;
 private DauMuc title;

 public Date getResevDate() {
	return resevDate;
}
public void setResevDate(Date resevDate) {
	this.resevDate = resevDate;
}
public DauMuc getTitle() {
	return title;
}
public void setTitle(DauMuc title) {
	this.title = title;
}
public DauMuc read() {
	 return null;
 }
 public void write() {}
}
