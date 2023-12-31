package  com.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import  com.dao.connDatabase.DBconnection;
import com.dao.interfaces.DemandeDao;
import com.modules.Demande;

public class DemandeDaoImp implements DemandeDao {
	Connection connection;

	@Override
	public int getMax() throws SQLException {

		ResultSet rs = null;

		String query = "SELECT count(*) FROM demande";
		int max = 0;

		try {
			
			connection = DBconnection.getConnection();
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			max = rs.getInt(1);
			rs.close();

		} catch (SQLException e) {
		
			e.printStackTrace();
		} 

		return max;
	}

	@Override
	public List<Demande> FindMesDemandes(int id_user) throws SQLException {

		connection = DBconnection.getConnection();
		ResultSet rs = null;
		

		
		PreparedStatement preparedStmt = null;

		String query = "SELECT etat_demande , id_demande , id_vehicule , id_user" + " FROM demande where id_user=" + id_user
				+ " order by id_demande desc";

		preparedStmt = connection.prepareStatement(query);

		rs = preparedStmt.executeQuery();

		List<Demande> list = new ArrayList<Demande>();

		String etat_demande;
		int id_demande;
		int id_vehicule;

		Demande demande = new Demande();

		while (rs.next()) {
			id_vehicule = rs.getInt("id_vehicule");
			id_demande = rs.getInt("id_demande");
			etat_demande = rs.getString("etat_demande");

			demande = new Demande(id_demande, id_user, id_vehicule, etat_demande);
			list.add(demande);
		}

		return list;
	}

	@Override
	public Demande FindDemandeById(int id_demande) throws SQLException {
		connection = DBconnection.getConnection();
		ResultSet rs = null;
		

		connection = DBconnection.getConnection();
		PreparedStatement preparedStmt = null;

		String query = "SELECT id_vehicule,id_demande,id_vehicule,id_user" + " FROM demande where id_demande="+id_demande;

		preparedStmt = connection.prepareStatement(query);

		rs = preparedStmt.executeQuery();

		String etat_demande;
		int id_user;
		int id_vehicule;

		Demande demande = new Demande();

		rs.next();
		id_vehicule = rs.getInt("id_vehicule");
		id_user = rs.getInt("id_user");
		etat_demande = rs.getString("etat_demande");

		demande = new Demande(id_demande, id_user, id_vehicule, etat_demande);
	

	return demande;

	}

	@Override
	public List<Demande> FindMesDemandesById_Vehicule(int id_vehicule) throws SQLException {
		connection = DBconnection.getConnection();
		ResultSet rs = null;
		

		connection = DBconnection.getConnection();
		PreparedStatement preparedStmt = null;

		String query = "SELECT id_vehicule,id_demande,etat_demande,id_user" + " FROM demande where id_vehicule=" + id_vehicule
				+ " order by id_demande desc";

		preparedStmt = connection.prepareStatement(query);

		rs = preparedStmt.executeQuery();

		List<Demande> list = new ArrayList<Demande>();

		String etat_demande;
		int id_demande;
		int id_user;

		Demande demande = new Demande();

		while (rs.next()) {
			id_user = rs.getInt("id_user");
			id_demande = rs.getInt("id_demande");
			etat_demande = rs.getString("etat_demande");

			demande = new Demande(id_demande, id_user, id_vehicule, etat_demande);
			list.add(demande);
		}

		return list;
	}

	@Override
	public void DeleteDemandeById(int id_demande) throws SQLException {
		PreparedStatement preparedStmt = null;
		
		connection = DBconnection.getConnection();
		String query = "delete from demande where id_demande=" + id_demande;
		preparedStmt = connection.prepareStatement(query);

		preparedStmt.execute();
	}

	@Override
	public Demande CreateDemande(Demande demande) throws SQLException {
		PreparedStatement preparedStmt = null;
		
		connection = DBconnection.getConnection();
		String query = "INSERT INTO demande ( id_vehicule,id_user,etat_demande) " + "VALUES ( ?, ?, ?)";
		preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	 
		preparedStmt.setInt(1, demande.getId_vehicule());
		preparedStmt.setInt(2, demande.getId_user());
		preparedStmt.setString(3, demande.getEtat_demande());
	 

		preparedStmt.execute();

		return demande;
	}
	
	
	
	
	
	@Override
	public void UpdateDemandeEtat(String etat_demande,int id) throws SQLException {
		PreparedStatement preparedStmt = null;
		
		connection = DBconnection.getConnection();
		String query = "update demande set etat_demande=? where id_demande="+id;
		preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStmt.setString(1, etat_demande);


		preparedStmt.execute();

		
	}

	@Override
	public int getCountDemande(int id) {
		 int u = 0;
	        try {
	            Connection con = DBconnection.getConnection();
	            PreparedStatement ps = con.prepareStatement("select count(*)as total from demande where id_user="+id);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                u=rs.getInt("total");
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return u;
	}

}
