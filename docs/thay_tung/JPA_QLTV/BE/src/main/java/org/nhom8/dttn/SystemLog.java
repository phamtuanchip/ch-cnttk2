package org.nhom8.dttn;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.nhom8.tienich.BaseEntity;

@Entity
public class SystemLog extends BaseEntity{
  
 private Date logTime;
 private String tile;
 private String description;
 
 public SystemLog(){
	 logTime = new Date();
 }
 
 public SystemLog(String tile, String desc){
	 super();
	 this.tile = tile;
	 this.description = desc;
 }


public Date getLogTime() {
	return logTime;
}

public void setLogTime(Date logTime) {
	this.logTime = logTime;
}

public String getTile() {
	return tile;
}

public void setTile(String tile) {
	this.tile = tile;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}
}
