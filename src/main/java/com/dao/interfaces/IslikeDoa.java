package com.dao.interfaces;

import java.sql.SQLException;


import java.util.List;
 
import com.modules.IslikeClass;


public interface IslikeDoa {

	public List<IslikeClass> FindLesLike( ) throws SQLException;


	public int CreateLike(IslikeClass islike) throws SQLException;
	public int showlike(int id_avis) throws SQLException;
	public int showdislike(int id_avis) throws SQLException;

	public int checklike(IslikeClass like) throws SQLException;
	public int checkDislike(IslikeClass like) throws SQLException;

 
}
