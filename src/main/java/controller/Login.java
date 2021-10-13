package controller;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Manageruser;
import Model.User;


@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/Home/*" })
public class Login implements Filter {

	public Login() {
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		Manageruser authUser = (Manageruser) servletRequest.getSession().getAttribute("user1");
		if (authUser == null) {
			((HttpServletResponse) response).sendRedirect("http://localhost:8080/Toolpdt/login-google");
		} else {
			chain.doFilter(servletRequest, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
