package org.nhom8.dttn;

import javax.persistence.Entity;

@Entity
public class NguoiMuon extends ThongTinBanDoc {
	private String cardNum;
	
	@Override
	public String toString() {
		return "Nguoi [id=" + getId() + ", name=" + getName() + ", card=" + getCardNum() + "]";
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
}	
 
