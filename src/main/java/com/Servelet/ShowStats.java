/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servelet; 
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.implementations.VoitureDaoImp; 
import com.dao.interfaces.VoitureDao;
import com.dao.implementations.DemandeDaoImp; 
import com.dao.interfaces.DemandeDao;
import com.dao.implementations.UserDaoImp; 
import com.dao.interfaces.UserDao; 
import com.dao.implementations.ChatDao;
/**
 *
 * @author AdminCH
 */
@WebServlet(name = "ShowStats", urlPatterns = {"/ShowStats"})
public class ShowStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ChatDao cht=new ChatDao();
	HttpSession session;
	VoitureDao voituredao = new VoitureDaoImp();
	DemandeDao demandedao = new DemandeDaoImp();
	UserDao  userdao= new UserDaoImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	session = request.getSession();
        int totalvoit =  voituredao.getCountVoiture();

        int totaluser =  userdao.getCountUsers();
        int userId = (int) session.getAttribute("id");
int totalchat=cht.getCountChatUsers(userId);
        int totaldemand =  demandedao.getCountDemande(userId);

        request.setAttribute("totalvoit", totalvoit); 
        request.setAttribute("totalchat", totalchat);  
        request.setAttribute("totaluser", totaluser);  
        request.setAttribute("totaldemand", totaldemand);  
         this.getServletContext().getRequestDispatcher("/WEB-INF/statistics.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }

    
}
