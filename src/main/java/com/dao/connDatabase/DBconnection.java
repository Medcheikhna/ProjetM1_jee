package com.dao.connDatabase;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	private static DBconnection instance = new DBconnection();
	public static final String URL = "jdbc:mysql://localhost:3306/locvoiture?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	// notre constructeur 
	private DBconnection() {
			try { Class.forName(DRIVER_CLASS);} catch (ClassNotFoundException e) { e.printStackTrace();}		
	}// fin du constructeur



	private Connection createConnection() {
			Connection connection = null;
			try { connection = DriverManager.getConnection(URL, USER, PASSWORD);} catch (SQLException e) 
			{ System.out.println("ERROR: Unable to Connect to Database.");}
			return connection;
	}// fin de creation de connection


	public static Connection getConnection() {
			return instance.createConnection();
	}// fin de getConnection
	
	
 

}

//UPDATE
//carpricesc cp,
//carpricesd cpf
//SET
//cp.Status = cpf.Status,
//cp.pricePrediction = cpf.pricePrediction
//WHERE
//cp.country = cpf.country AND
//cp.productionDate = cpf.productionDate AND
//cp.model = cpf.model AND
//cp.priceEuro = cpf.priceEuro AND
//cp.milage = cpf.milage;
//
//
//UPDATE carpricesc cp
//SET cp.Status =  cpf.Status,
//cp.pricePrediction = cpf.pricePrediction
//
//FROM  carpricesc cp
//JOIN
//(SELECT *
//FROM carpricesd cpf GROUP BY cpf.country, cpf.productionDate,  cpf.model) AS b
//ON datatable.CampaignID = b.CampaignID
//AND datatable.Category = b.Category
//AND datatable.TopicNameHash = b.TopicNameHash

