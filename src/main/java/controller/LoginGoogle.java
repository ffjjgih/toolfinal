package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserServies;
import Model.GooglePojo;
import Model.Manageruser;
import Services.GoogleUtils;

@MultipartConfig()
@WebServlet("/login-google")
public class LoginGoogle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao.UserServies userLogin;

	public LoginGoogle() {
		this.userLogin = new UserServies();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			RequestDispatcher dis = request.getRequestDispatcher("/views/loginForm.jsp");
			dis.forward(request, response);
			return;
		}
		String token = GoogleUtils.getToken(code);
		GooglePojo googlePojo = GoogleUtils.getUserInfo(token);
		String email = googlePojo.getEmail();
		Manageruser us = userLogin.findByEmail(email);
		if (us != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user1", us);
			response.sendRedirect("http://localhost:8080/Toolpdt/Home");
		} else {
			RequestDispatcher dis = request.getRequestDispatcher("/views/loginForm.jsp");
			dis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
