package com.Servelet;

import java.io.File;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dao.implementations.UserDaoImp;
import com.dao.implementations.VoitureDaoImp;
import com.dao.interfaces.UserDao;
import com.dao.interfaces.VoitureDao;
import com.modules.User;
import com.modules.Voiture;

@WebServlet
public class Voiture2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserDao userdao = new UserDaoImp();
	VoitureDao voituredao = new VoitureDaoImp();
	HttpSession session;
	String forward = "";
	String action;
	String pathImg = "C:\\Users\\Republic Of Computer\\eclipse-workspace\\LocatonVoiture\\src\\main\\webapp\\res\\voiture";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		action = request.getParameter("action");
		if (session.getAttribute("user") != null) {
			if (action.equalsIgnoreCase("ajoutervoiture")) {
				forward = "/WEB-INF/veh.jsp";
			} else if (action.equalsIgnoreCase("mesvoitures")) {
				List<Voiture> list = new ArrayList<Voiture>();
				try {
					list = voituredao.findSpecificVoitureByEmail((String) session.getAttribute("user"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("list", list);

				forward = "/WEB-INF/Mesvoitures.jsp";
			} else if (action.equalsIgnoreCase("modifiermavoiture")) {

				String id = request.getParameter("id_vehicule");
				Voiture v = new Voiture();
				try {
					v = voituredao.findSpecificVoitureByid(Integer.parseInt(id));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("voiture", v);

				forward = "/WEB-INF/Mavoiture.jsp";
			} else if (action.equalsIgnoreCase("deletevoiture")) {
				String id = request.getParameter("id_vehicule");
				try {
					voituredao.DeleteVoiture(Integer.parseInt(id));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				forward = "/WEB-INF/done.jsp";
			}
		} else {
			forward = "/WEB-INF/login.jsp";
		}

		request.getRequestDispatcher(forward).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		action = request.getParameter("action");
		if (session.getAttribute("user") != null) {
			  if (action.equalsIgnoreCase("updateCar")) {
		

			 
			String marque =  request.getParameter("firstname");
			String modele = request.getParameter("lastname");
			String carburant =  request.getParameter("carburant");
			String boite_vitesse = request.getParameter("boite");
			String places =  request.getParameter("place");
			String ports = request.getParameter("ports");
			String kilometrage = request.getParameter("kilometrage");
			String ville =  request.getParameter("adress");
			String prix =  request.getParameter("prix");
			String lieu =  request.getParameter("lieu");
			String description = request.getParameter("description");
			String DATEDEB = request.getParameter("date1");
			String DATEFIN = request.getParameter("date2");
			String etat = request.getParameter("etat");
			String photo1 =   "1" + ".jpg";
			String photo2 =   "2" + ".jpg";
			String TYPE_VRHICULE = "";
			String IMMATRICULATION = "";
			User user = null;
			try {
				user = userdao.findSpecificUserByEmail((String) session.getAttribute("user"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String id= request.getParameter("id_voiture");
			System.out.println(id+"  marque "+marque+" modele  "+modele+" kilometrage "+kilometrage+"descrip"+description);
			Voiture voiture = new Voiture( Integer.parseInt(id), marque, modele, carburant,
					Integer.parseInt(kilometrage),
					IMMATRICULATION, TYPE_VRHICULE, photo1, photo2, boite_vitesse, 
					Integer.parseInt(places),
					Integer.parseInt(ports), user.getId_user(), DATEFIN, DATEDEB, 
					Integer.parseInt(prix),
					description, lieu, ville, etat);
			try {
				voituredao.UpdateVoiture(voiture);
				forward = "/WEB-INF/done.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			forward = "/WEB-INF/done.jsp";
		}
		else {	forward = "/WEB-INF/login.jsp";}

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
}