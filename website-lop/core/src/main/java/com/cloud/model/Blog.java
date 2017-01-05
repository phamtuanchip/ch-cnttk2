package com.cloud.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "blog")
public class Blog implements Serializable {
@Id	
private long id;
private Date created;
private Date publish;
private String tile;
private byte[] content;
private String creator;
private boolean isPublished = false;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public Date getCreated() {
	return created;
}
public void setCreated(Date created) {
	this.created = created;
}
public Date getPublish() {
	return publish;
}
public void setPublish(Date publish) {
	this.publish = publish;
}
public String getTile() {
	return tile;
}
public void setTile(String tile) {
	this.tile = tile;
}
public byte[] getContent() {
	return content;
}
public void setContent(byte[] content) {
	this.content = content;
}
public String getCreator() {
	return creator;
}
public void setCreator(String creator) {
	this.creator = creator;
}
public boolean isPublished() {
	return isPublished;
}
public void setPublished(boolean isPublished) {
	this.isPublished = isPublished;
}

}
