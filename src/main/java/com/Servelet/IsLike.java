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

import com.dao.implementations.IslikeDoaImp;
import com.dao.interfaces.IslikeDoa;
import com.modules.IslikeClass;

@WebServlet("/IsLike")
public class IsLike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String forward = "";
	String action = "";
	IslikeDoa likeDao = new IslikeDoaImp();
	HttpSession session;
	List<IslikeClass> list = new ArrayList<IslikeClass>();

	public IsLike() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		forward = "AvisServl?action=avis";
		session = request.getSession();
		int userId = (int) session.getAttribute("id");
		action = request.getParameter("action");
		if (action.equalsIgnoreCase("like")) {


			try {
				list = likeDao.FindLesLike();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list", list);

		} else if (action.equalsIgnoreCase("changelike")) {
			try {
				String user_id = request.getParameter("user_id");
				String avis_id = request.getParameter("id_avis");
				String click = request.getParameter("click");
				IslikeClass like = new IslikeClass(Integer.parseInt(user_id), Integer.parseInt(avis_id), click);
				
				if (likeDao.checklike(like) == 0) {
					likeDao.CreateLike(like);
					System.out.println("like inserr");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("changeDislike")) {
			try {
				String user_id = request.getParameter("user_id");
				String avis_id = request.getParameter("id_avis");
				String click = request.getParameter("click");

				IslikeClass like = new IslikeClass(Integer.parseInt(user_id), Integer.parseInt(avis_id), click);
			
				if (likeDao.checkDislike(like) == 0) {
					likeDao.CreateLike(like);
					System.out.println("like inserr");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
		IslikeClass like = null;
		String user_id = request.getParameter("user_id");
		String avis_id = request.getParameter("avis_id");
		String islike = request.getParameter("islike");

		if ((session.getAttribute("user") != null)) {

			like = new IslikeClass(Integer.parseInt(user_id), Integer.parseInt(avis_id), islike);
		}
		try {
			likeDao.CreateLike(like);
			System.out.println("like inserr");
			forward = "/WEB-INF/avis.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher(forward).forward(request, response);

	}
}
