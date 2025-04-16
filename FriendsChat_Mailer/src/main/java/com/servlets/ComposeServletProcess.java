package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ComposeDao;


/**
 * Servlet implementation class ComposeServletProcess
 */
@WebServlet("/ComposeServletProcess")
public class ComposeServletProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);

		String receiver = request.getParameter("to");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		message = message.replaceAll("\n", "<br/>");
		String email = (String) request.getSession(false).getAttribute("email");

		int i = ComposeDao.save(email, receiver, subject, message);
		if (i > 0) {
			request.setAttribute("msg", "message successfully sent");
			request.getRequestDispatcher("ComposeServlet").forward(request, response);
		}

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();

	}

}
