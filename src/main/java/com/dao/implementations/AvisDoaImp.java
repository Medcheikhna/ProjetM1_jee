package com.dao.implementations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dao.connDatabase.DBconnection;
import com.dao.interfaces.AvisDoa;
import com.modules.Avis;
import com.modules.Demande;
import com.modules.User;
import com.modules.Voiture;

public class AvisDoaImp implements AvisDoa {
	Connection connection;

	@Override
	public List<Avis> FindLesAvis() throws SQLException {

		connection = DBconnection.getConnection();
		ResultSet rs = null;

		PreparedStatement preparedStmt = null;

		String query = "SELECT * FROM avis order by id_avis desc";

		preparedStmt = connection.prepareStatement(query);

		rs = preparedStmt.executeQuery();

		List<Avis> list = new ArrayList<Avis>();
		int id;
		String message;
		String nom;
		String email, date;

		Avis avis = new Avis();

		while (rs.next()) {
			id = rs.getInt("id_avis");
			email = rs.getString("email");
			nom = rs.getString("nom");
			message = rs.getString("message");
			date = rs.getString("date");
			avis = new Avis(id, nom, email, message, date);
			list.add(avis);
		}

		return list;
	}

	@Override
	public void DeleteAvisById(int id_avis) throws SQLException {
		PreparedStatement preparedStmt = null;

		connection = DBconnection.getConnection();
		String query = "delete from avis  where id_avis = " + id_avis;
		preparedStmt = connection.prepareStatement(query);

		preparedStmt.execute();
	}

	public Avis findSpecificAvisByid(int id) throws SQLException {
		connection = DBconnection.getConnection();
		ResultSet rs = null;

		connection = DBconnection.getConnection();
		PreparedStatement preparedStmt = null;

		String query = "SELECT * FROM avis where  id_avis=" + id;

		preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		rs = preparedStmt.executeQuery();
		rs.next();
		int id_avis = rs.getInt("id_avis");
		String message = rs.getString("message");
		String email = rs.getString("email");
		String nom = rs.getString("nom");
		String date = rs.getString("date");
		Avis avis = new Avis(id_avis, nom, email, message, date);

		return avis;
	}

	@Override
	public int CreateAvis(Avis avis) throws SQLException {

		PreparedStatement ps = null;
		connection = DBconnection.getConnection();
		String query = "INSERT INTO avis (nom,email,message,date)  VALUES (?,?,?,?)";
		ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, avis.getNom());
		ps.setString(2, avis.getEmail());
		ps.setString(3, avis.getMessage());
		ps.setString(4, avis.getDate());
		ps.execute();

		return 0;
	}

}
