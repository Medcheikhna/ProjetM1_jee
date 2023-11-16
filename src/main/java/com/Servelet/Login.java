package com.Servelet;

import java.io.IOException;



import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.implementations.UserDaoImp;
import com.modules.User;

/**
 * Servlet implementation class Login
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDaoImp userdao= new UserDaoImp();
	HttpSession session;

	String forward="/WEB-INF/login.jsp";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();

		// TODO Auto-generated method stub

		// test valid
		if(session.getAttribute("user")==null) {
				forward="/WEB-INF/login.jsp?email=";
		}else {
			forward="/WEB-INF/index.jsp";
		}
		request.getRequestDispatcher(forward).forward(request, response);

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login");
		session = request.getSession();
		if(session.getAttribute("user")==null) {

		String  email=request.getParameter("email");
		String  password=request.getParameter("password");
		Boolean exist=false;
		forward="/WEB-INF/login.jsp?error=e&email="+email;
		exist=userdao.user_login(email, password);

		if(exist){
			System.out.println("existe");
			User user=new User();
			try {
				user=userdao.findSpecificUserByEmail(email);
				if(user.getPassword().equals(password)) {
					System.out.println("true");
					session.setAttribute("user", email);
					session.setAttribute("id", user.getId_user());
					String login_name=user.getLogin();
					session.setAttribute("login_name",login_name );		
				forward="/WEB-INF/index.jsp";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}
		else {
			forward="/WEB-INF/index.jsp";
		}
		request.getRequestDispatcher(forward).forward(request, response);

	}}


