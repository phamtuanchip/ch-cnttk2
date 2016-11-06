package org.nhom8.dttn;

import org.nhom8.csdl.ObjectID;
import org.nhom8.csdl.Persistent;

import com.sun.corba.se.spi.ior.ObjectId;

public class HangMuc extends Persistent {
 private int itemId;
 private ObjectID title;
 private NguoiMuon loan;
 
 public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
}
public ObjectID getTitle() {
	return title;
}
public void setTitle(ObjectID title) {
	this.title = title;
}
public NguoiMuon getLoan() {
	return loan;
}
public void setLoan(NguoiMuon loan) {
	this.loan = loan;
}
public boolean isBrrowed() {
	return isBrrowed;
}
public void setBrrowed(boolean isBrrowed) {
	this.isBrrowed = isBrrowed;
}
private boolean isBrrowed;
 
 public Object finOnItem() {
	 return null;
 }
 public Object findOnTitle() {
	 return null;
 }
 
}
