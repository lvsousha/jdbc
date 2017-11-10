package com.lvdousha.jdbc.mybatis.model;

import java.util.Date;
import java.util.List;

public class User {

	private int id;
	private String name;
	private String password;
	private Date created;
	private Date updated;
	private List<UserDetail> userDetails; 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<UserDetail> getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}
	
}
