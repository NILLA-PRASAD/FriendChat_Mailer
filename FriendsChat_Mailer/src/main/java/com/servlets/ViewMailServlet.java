package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ConProviderDao;

/**
 * Servlet implementation class ViewMailServlet
 */
@WebServlet("/ViewMailServlet")
public class ViewMailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("home.html").include(request, response);

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("register.html");
		} else {
			String email = (String) session.getAttribute("email");
			out.print("<span style='float:right'>Hi, " + email + "</span>");

			int id = Integer.parseInt(request.getParameter("id"));

			try {
				Connection con = ConProviderDao.getConnection();
				PreparedStatement ps = con.prepareStatement("delete from frndschat_message where id=?");
				ps.setInt(1, id);
				int i = ps.executeUpdate();
				if (i > 0) {
					request.setAttribute("msg", "Mail successfully deleted permanently!");
					request.getRequestDispatcher("TrashServlet").forward(request, response);
				}
				con.close();
			} catch (Exception e) {
				out.print(e);
			}
		}

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();

	}
}
