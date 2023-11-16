package com.Servelet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.implementations.AvisDoaImp;
import com.dao.implementations.IslikeDoaImp;
import com.dao.interfaces.AvisDoa;
import com.dao.interfaces.IslikeDoa;
import com.modules.Avis;

@WebServlet("/AvisServl")
public class AvisServl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IslikeDoa likeDao = new IslikeDoaImp();
	String forward = "";
	String action = "";
	AvisDoa avisDao = new AvisDoaImp();
	HttpSession session;
//
//	IslikeDoa like =   new IslikeDoaImp();
	public AvisServl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("like", likeDao);
		session = request.getSession();
		int userId = (int) session.getAttribute("id");
//////////////////////request.getSession will get the information in the session and session.getAttribute for get or set sepcific value
		// request.getParameter get the vlue of the url like ?id=5
////////////////////////if i dont do	request.setAttribute("list", list); it will show error becose they dot know list in avis.jsp
////////////////////////the scope of if shold have evry one this
		action = request.getParameter("action");
		if (action.equalsIgnoreCase("avis")) {
			List<Avis> list = new ArrayList<Avis>();
			forward = "/WEB-INF/avis.jsp";
			
			try {
				list = avisDao.FindLesAvis();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list", list);

		}

		////////////////////// forward is for evry time the doget end will redireet to
		////////////////////// respone of jsp
		request.getRequestDispatcher(forward).forward(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		Avis avis = null;
		String email = request.getParameter("email");
		String nom = request.getParameter("nom");
		String message = request.getParameter("message");

		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new Date(date.getTime());
		String Time = sqlDate + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		if ((session.getAttribute("user") != null)) {
			int userId = (int) session.getAttribute("id");

			avis = new Avis(nom, email, message, Time);
		}
		try {
			avisDao.CreateAvis(avis);
			System.out.println("Avis inserr");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		forward = "/WEB-INF/index.jsp";
		request.getRequestDispatcher(forward).forward(request, response);

	}
}