package com.modules;

public class IslikeClass {
private int id;
private int user_id,avis_id;
private String islike;
 
	public IslikeClass(int id,int user_id,int avis_id,String islike) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.islike=islike;
		this.user_id=user_id;
		this.avis_id=avis_id;
	}
	public IslikeClass(int user_id,int avis_id,String islike) {
		// TODO Auto-generated constructor stub
		this.islike=islike;
		this.user_id=user_id;
		this.avis_id=avis_id;
	}
public IslikeClass() {
		// TODO Auto-generated constructor stub
	}
public int getId() {
	return this.id;
}
public int getUser() {
	return this.user_id;
	
}

public int getAvis() {
	return this.avis_id;
}
public String getIslike() {
	return this.islike;
}
}
