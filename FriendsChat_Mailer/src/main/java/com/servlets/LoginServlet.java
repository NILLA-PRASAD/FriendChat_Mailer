package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//request.getRequestDispatcher("header.html").include(request, response);

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (LoginDao.validate(email, password)) {
			out.print("you are successfully logged in!");
			request.getSession().setAttribute("login", "true");
			request.getSession().setAttribute("email", email);
			response.sendRedirect("home.html");

		} else {
			
			request.getRequestDispatcher("login.html").include(request, response);
			out.print("<center><p>Sorry, username or password error</p></center>");
		}

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
