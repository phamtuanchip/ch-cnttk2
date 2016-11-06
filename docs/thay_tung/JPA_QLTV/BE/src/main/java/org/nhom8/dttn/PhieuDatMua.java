package org.nhom8.dttn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
public class PhieuDatMua extends Phieu{
	 
	private Long itemId;
	private Long resevId;
	private String publish;
	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
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

	private String publisher;
	private Date reserveDate;
//	@OneToMany 
//    @JoinColumn(name = "resevId", referencedColumnName = "itemId") 
////    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE }) 
//    @LazyCollection(LazyCollectionOption.FALSE) 
	@Transient
	private List<DauMuc> items = new ArrayList<DauMuc>();
	
	public boolean addItem(DauMuc d) {
		itemId = d.getId();
		resevId = d.getId();
		return items.add(d);
	}
	 
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public void setItems(List<DauMuc> items) {
		this.items = items;
	}

	public List<DauMuc> getItems() {
		// TODO Auto-generated method stub
		return items;
	}
	
	@Override
	public String toString() {
		//return "Phieu mua [id=" + getId() + ", publicsher=" + getPublisher() + ", num =" + getItems().size() + "]";
		return "Phieu mua [id=" + getId() + ", publicsher=" + getPublisher() + ", num = sqlite no support relation]";
	}
}
