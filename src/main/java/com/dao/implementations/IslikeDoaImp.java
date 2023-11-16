package com.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.connDatabase.DBconnection;
import com.dao.interfaces.IslikeDoa;
import com.modules.IslikeClass;

public class IslikeDoaImp implements IslikeDoa {
	Connection connection;

	public IslikeDoaImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IslikeClass> FindLesLike() throws SQLException {
		connection = DBconnection.getConnection();
		ResultSet rs = null;

		PreparedStatement preparedStmt = null;

		String query = "SELECT * FROM islike order by id  desc";

		preparedStmt = connection.prepareStatement(query);

		rs = preparedStmt.executeQuery();

		List<IslikeClass> list = new ArrayList<IslikeClass>();
		int id, user_id, avis_id;
		String islike2;

		IslikeClass islike = new IslikeClass();

		while (rs.next()) {
			id = rs.getInt("id");
			user_id = rs.getInt("user_id");
			avis_id = rs.getInt("avis_id");
			islike2 = rs.getString("islike");

			islike = new IslikeClass(id, user_id, avis_id, islike2);
			list.add(islike);
		}

		return list;
	}

	@Override
	public int CreateLike(IslikeClass islike) throws SQLException {
		PreparedStatement ps = null;
		connection = DBconnection.getConnection();
		String query = "INSERT INTO islike (user_id,avis_id,islike)  VALUES (?,?,?)";
		ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, islike.getUser());
		ps.setInt(2, islike.getAvis());
		ps.setString(3, islike.getIslike());

		ps.execute();

		return 0;
	}

	@Override
	public int checklike(IslikeClass like) throws SQLException {
		ResultSet rs = null;
		int count = 0;
		connection = DBconnection.getConnection();
		PreparedStatement preparedStmt = null;
		try {
			String query1 = "delete from islike where  avis_id=" + like.getAvis() + " and user_id=" + like.getUser();
			System.out.println("deleted" + like.getUser() + "and avis_id=" + like.getAvis());
			preparedStmt = connection.prepareStatement(query1);
			preparedStmt.execute();

			String query = "SELECT count(user_id) FROM islike where  islike=" + like.getIslike() + " and avis_id= "
					+ like.getAvis();
			preparedStmt = connection.prepareStatement(query);

			rs = preparedStmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {

		}

		return count;
	}

	@Override
	public int checkDislike(IslikeClass like) throws SQLException {
		ResultSet rs = null;
		int count = 0;
		connection = DBconnection.getConnection();
		PreparedStatement preparedStmt = null;
		try {

			String query1 = "delete from islike where   avis_id=" + like.getAvis() + " and user_id=" + like.getUser();
			System.out.println("deleted" + like.getUser() + "and avis_id=" + like.getAvis());
			preparedStmt = connection.prepareStatement(query1);
			preparedStmt.execute();
			String query = "SELECT count(user_id) FROM islike where  islike=" + like.getIslike() + "and avis_id= "
					+ like.getAvis();
			preparedStmt = connection.prepareStatement(query);

			rs = preparedStmt.executeQuery();
			rs.next();
			count = rs.getInt(1);

			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {

		}

		return count;
	}

	@Override
	public int showlike(int id_avis) throws SQLException {
		connection = DBconnection.getConnection();
		ResultSet rs = null;

		PreparedStatement preparedStmt = null;

		int count = 0;
		String query = "select count(islike) from islike where islike='true' and avis_id=" + id_avis;
		try {
			preparedStmt = connection.prepareStatement(query);

			rs = preparedStmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int showdislike(int id_avis) throws SQLException {
		connection = DBconnection.getConnection();
		ResultSet rs = null;

		int count = 0;

		PreparedStatement preparedStmt = null;

		try {
			String query = "select count(islike) from islike where islike='false' and avis_id=" + id_avis;
			preparedStmt = connection.prepareStatement(query);

			rs = preparedStmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
