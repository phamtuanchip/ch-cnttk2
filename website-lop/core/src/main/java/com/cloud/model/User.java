package com.cloud.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4142835870566396413L;
	private String username;
	private String password;
	private Boolean enabled;
	private String roleCode;
	private String roleString;
	private String cityCode;
	private String hostAirlines;
	@Id
	private String id;
	
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public String getRoleCode() {
		return roleCode;
	}


	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	public String getRoleString() {
		return roleString;
	}


	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}


	public String getCityCode() {
		return cityCode;
	}


	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


	public String getHostAirlines() {
		return hostAirlines;
	}


	public void setHostAirlines(String hostAirlines) {
		this.hostAirlines = hostAirlines;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	 

}
