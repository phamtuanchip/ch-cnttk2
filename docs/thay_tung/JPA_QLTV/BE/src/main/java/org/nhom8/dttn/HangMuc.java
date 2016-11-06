package org.nhom8.dttn;

public class HangMuc  {
 private int itemId;
 private NguoiMuon loan;
 
 public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
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
