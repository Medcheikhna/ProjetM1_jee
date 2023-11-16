package com.modules;

public class Avis {
	private String message;
	private String email;
	private String nom;
	private String date;
	private int id;


	
	public Avis(int id, String nom, String email, String message, String date) {
		this.nom = nom;
		this.id = id;
		this.email = email;
		this.message = message;
		this.date = date;

		// TODO Auto-generated constructor stub
	}

	public Avis(String nom, String email, String message, String date) {
		this.nom = nom;
		this.email = email;
		this.date = date;
		this.message = message;
	}

	
	public Avis() {
		super();
	}

	public String getDate() {
		return this.date;
	}



	public int getId() {
		return this.id;
	}



	public String getNom() {
		return this.nom;

	}

	public String getEmail() {
		return this.email;
	}

	public String getMessage() {
		return this.message;
	}

	void setNome(String nom) {
		this.nom = nom;
	}

	void setEmail(String email) {
		this.email = email;
	}

	void setMessage(String message) {
		this.message = message;
	}

}
