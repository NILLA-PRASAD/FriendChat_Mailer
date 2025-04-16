package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ConProviderDao;


/**
 * Servlet implementation class InboxServlet
 */
@WebServlet("/InboxServlet")
public class InboxServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			request.getRequestDispatcher("header.html").include(request, response);
			request.getRequestDispatcher("home.html").include(request, response);
			
			HttpSession session=request.getSession(false);
			if(session==null){
				response.sendRedirect("register.html");
			}else{
				String email=(String)session.getAttribute("email");
				out.print("<span style='float:right'>Hi, "+email+"</span>");
				out.print("<h1>Inbox</h1>");
				
				
				try{
					Connection con=ConProviderDao.getConnection();
					PreparedStatement ps=con.prepareStatement("select * from frndschat_message where receiver=? and trash='no' order by id desc");
					ps.setString(1,email);
					ResultSet rs=ps.executeQuery();
					out.print("<table border='1' style='width:700px;'>");
					out.print("<tr style='background-color:grey;color:white'><td>Sender</td><td>Subject</td></tr>");
					while(rs.next()){
						out.print("<tr><td>"+rs.getString("sender")+"</td><td><a href='ViewMailServlet?id="+rs.getString(1)+"'>"+rs.getString("subject")+"</a></td></tr>");
					}
					out.print("</table>");
					
					con.close();
				}catch(Exception e){out.print(e);}
			}
			
			
			
			request.getRequestDispatcher("footer.html").include(request, response);
			out.close();
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request,response);
		}

	}

	


