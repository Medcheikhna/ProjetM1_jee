package com.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.modules.Avis;
import com.modules.Voiture;

public interface AvisDoa {

 
	public List<Avis> FindLesAvis( ) throws SQLException;


	public void DeleteAvisById(int id_avis) throws SQLException;

	public int CreateAvis(Avis avis) throws SQLException;

	

}
