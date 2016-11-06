package org.nhom8.dttn;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class PhieuNhacTra extends Phieu {
private Long taiLieu;
private Long nguoiMuon;
private Date returnDate;

public Long getTaiLieu() {
	return taiLieu;
}
public void setTaiLieu(Long taiLieu) {
	this.taiLieu = taiLieu;
}
public Long getNguoiMuon() {
	return nguoiMuon;
}
public void setNguoiMuon(Long nguoiMuon) {
	this.nguoiMuon = nguoiMuon;
}
@Override
public String toString() {
	//return "Phieu [id=" + getId() + ", reader=" + getReader().getName() + ", card=" + getReader().getCardNum() + "]";
	return "Phieu tra [id=" + getId() + ", reader= sqlite no supported, card= sqlite no supported]";
}
public Date getReturnDate() {
	return returnDate;
}
public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
}
}
