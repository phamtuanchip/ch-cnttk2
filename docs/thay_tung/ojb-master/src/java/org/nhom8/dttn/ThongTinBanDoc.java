package org.nhom8.dttn;

import org.nhom8.csdl.ObjectID;
import org.nhom8.csdl.Persistent;

public class ThongTinBanDoc extends Persistent {
 private String name;
 private String address;
 private String cardNum;
 private ObjectID[] loans;
 private ObjectID[] reservation;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCardNum() {
	return cardNum;
}
public void setCardNum(String cardNum) {
	this.cardNum = cardNum;
}
public ObjectID[] getLoans() {
	return loans;
}
public void setLoans(ObjectID[] loans) {
	this.loans = loans;
}
public NguoiMuon getReservation() {
	return null;
}
public void setReservation(ObjectID[] reservation) {
	this.reservation = reservation;
}
 
 
}
