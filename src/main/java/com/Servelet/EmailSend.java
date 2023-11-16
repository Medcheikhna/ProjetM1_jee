package com.Servelet;


	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */
  
	import java.io.IOException;
	  
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	 
	/**
	 * A servlet that takes message details from user and send it as a new e-mail
	 * through an SMTP server.
	 *
	 * @author www.codejava.net
	 *
	 */
	@WebServlet("/EmailSending")
	public class EmailSend extends HttpServlet {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String host;
	    private String port;
	    private String user;
	    private String pass;
	 
	    public void init() {
	        host = "localhost";
	        port = "8082";
	        user = "1email4project@gmail.com";
	        pass = "1EMAIL4project";
	    }
	 
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        // reads form fields
	        String nom = request.getParameter("nom");
	        String email = request.getParameter("email");
	        String message = request.getParameter("message");
	 
	        String resultMessage = "";
	 
	        try {
	            EmailUtil.sendEmail(host, port, user, pass, email, nom,
	                    message);
	            resultMessage = "The e-mail was sent successfully";
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            resultMessage = "There were an error: " + ex.getMessage();
	        } finally {
	            request.setAttribute("Message", resultMessage);
	            getServletContext().getRequestDispatcher("/jsp/SendEmails.jsp").forward(
	                    request, response);
	        }
	    }
	}