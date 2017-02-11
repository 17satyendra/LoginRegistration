package com.user.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dto.UserRegDao;

/**
 * @author bridgeit Satyendra Singh.
 * In this class extend {@link HttpServlet} having two methods one is doPost for Registration form to post value.
 * Another is doGet method for get value from login form.
 */
@SuppressWarnings("serial")
public class RegSerVlet extends HttpServlet {
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
									throws ServletException, IOException {

		String fn = req.getParameter("fname");
		String ln = req.getParameter("lname");
		String em = req.getParameter("email");
		String un = req.getParameter("uname");
		String pas = req.getParameter("pass");

		UserReg ur = new UserReg(fn, ln, em, un, pas);

		UserRegDao dao = new UserRegDao();
		dao.saveUser(ur);

		PrintWriter out = resp.getWriter();
		out.println("<html><body bgcolor='green'>" + "<h1>you Registered!!!!!" + " " + fn +"<br><a href=\"Login.jsp\"><h5>Back To login Page<h5></a>"
				+"<body><html>");
		out.close();
		System.out.println("Data saved");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("id");
		String password = req.getParameter("password");

		UserRegDao dao = new UserRegDao();
		UserReg userReg = dao.validation(user, password);

		if (userReg != null) {

			System.out.println("Loging...");
			RequestDispatcher rd = req.getRequestDispatcher("/ success.jsp");
			rd.forward(req, resp);
		} else {
			req.getRequestDispatcher("/fail.jsp").forward(req, resp);
		}
	}
}