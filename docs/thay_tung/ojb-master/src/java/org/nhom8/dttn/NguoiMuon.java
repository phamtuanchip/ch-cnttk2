package org.nhom8.dttn;

import org.nhom8.csdl.ObjectID;
import org.nhom8.csdl.Persistent;

public class NguoiMuon extends Persistent {
 private String cardNum;
 private HangMuc[] items;
 private ObjectID[] borrower ;
public String getCardNum() {
	return cardNum;
}
public void setCardNum(String cardNum) {
	this.cardNum = cardNum;
}
public HangMuc[] getItems() {
	return items;
}
public void setItems(HangMuc[] items) {
	this.items = items;
}
public ObjectID[] getBorrower() {
	return borrower;
}
public void setBorrower(ObjectID[] borrower) {
	this.borrower = borrower;
}
 
 public NguoiMuon read(){
	 return this;
 }
 
 public void write() {
 }
}
