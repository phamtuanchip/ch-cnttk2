package org.nhom8.dttn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class PhieuDatMuon extends Phieu {

	
	private Date resevDate;
	private int title;
	
	//@Column(nullable=false)
	private Long resevId;
	private Long readerId;
	private Long itemId;
	
	@OneToOne
	@JoinColumn(name = "id", referencedColumnName="readerId")
	@LazyCollection(LazyCollectionOption.FALSE) 
	private NguoiMuon reader;
	
	@OneToMany 
    @JoinColumn(name = "resevId", referencedColumnName = "itemId") 
//    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE }) 
    @LazyCollection(LazyCollectionOption.FALSE) 
	private List<DauMuc> items = new ArrayList<DauMuc>();
	
	public boolean addItem(DauMuc d) {
		return items.add(d);
	}
	 
	public Date getResevDate() {
		return resevDate;
	}
	public void setResevDate(Date resevDate) {
		this.resevDate = resevDate;
	}
	public int getTitle() {
		return title;
	}
	public void setTitle(int title) {
		this.title = title;
	}
	public Long getReaderId() {
		return readerId;
	}
	public void setReaderId(Long readerId) {
		this.readerId = readerId;
	}
	
	@Override
	public String toString() {
		//return "Phieu [id=" + getId() + ", reader=" + getReader().getName() + ", card=" + getReader().getCardNum() + "]";
		return "Phieu [id=" + getId() + ", reader= sqlite no supported, card= sqlite no supported]";
	}
	public List<DauMuc> getItems() {
		return items;
	}
	public void setItems(List<DauMuc> items) {
		this.items = items;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getResevId() {
		return resevId;
	}
	public void setResevId(Long resevId) {
		this.resevId = resevId;
	}
	public NguoiMuon getReader() {
		return reader;
	}
	public void setReader(NguoiMuon reader) {
		this.reader = reader;
	}
}
