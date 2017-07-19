package com.truecorp.dashboard.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.truecorp.dashboard.model.Authorize;
import com.truecorp.dashboard.service.LoginService;
import com.truecorp.dashboard.util.Messages;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "login":
			doLogin(request, response);
			break;
		case "logout":
			doLogout(request, response);
			break;
		case "view":
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
			break;
		}
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		Authorize auth = null;

		try {
			auth = loginService.doLogin(username, password);

			if (auth != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", auth);
				request.setAttribute("msg", Messages.LOGIN_SUCCESSFUL);
				request.getRequestDispatcher("HomeServlet?action=view").forward(request, response);
			} else {
				request.setAttribute("msg", Messages.LOGIN_FAILURE);
				request.getRequestDispatcher("pages/login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("msg", Messages.INTERNAL_SERVER_ERROR);
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		}
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	}

}
