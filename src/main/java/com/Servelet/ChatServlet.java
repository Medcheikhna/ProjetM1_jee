package com.Servelet;

 
import com.modules.Chat;
import com.dao.implementations.ChatDao;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
/**
 *
 * @author AdminCH
 */
@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends HttpServlet {
	  String receiver="";
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	receiver = request.getParameter("receiver");
            List<Chat> list = ChatDao.getAllChats(Integer.parseInt(receiver));
            request.setAttribute("list", list);
           
            this.getServletContext().getRequestDispatcher("/WEB-INF/Inbox.jsp").forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	

        String action= (String) request.getParameter("envoiyer");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Chat t=null;
        String content = request.getParameter("content");
        try {
            date = dateFormat.parse(java.time.LocalDate.now().toString());
            long ms = timeFormat.parse(""+java.time.LocalTime.now()).getTime();
            Time time = new Time(ms);
            HttpSession session = request.getSession();
            String memberid = session.getAttribute("id").toString();
            int id = Integer.parseInt(memberid);
            
            
            if(content.length()!=0) {
            	t   = new Chat(id, Integer.parseInt(receiver),content,date,time);
//                t.setFrom(id);
//                t.setTo(1);
//                t.setDate(date);
//                t.setTime(time);
//                t.setContent(content);
                ChatDao.savenew(t) ;
                System.out.println( "Successfful !!");
               
            }else{
                System.out.println("Faiiiiil !!");
            
            }
        } catch (ParseException ex) {
            Logger.getLogger(ChatServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        List<Chat> list = ChatDao.getAllChats(Integer.parseInt(receiver));
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/Inbox.jsp").forward(request, response);

//        this.getServletContext().getRequestDispatcher("/WEB-INF/Inbox.jsp").forward(request, response);
        
    }

   
}
