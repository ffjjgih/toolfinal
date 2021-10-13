package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Daokht;
import Model.DsThi;

@WebServlet("/searchkht")
public class SearchKHTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Daokht dao;
	private List<DsThi> lst;
	
    public SearchKHTController() {
        super();
        this.dao = new Daokht();
        this.lst = new ArrayList<DsThi>();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idClass = request.getParameter("txtSearch");
		this.lst = dao.findKHTByClass(idClass);
		if (!lst.isEmpty()) {
			request.setAttribute("suc", "Tìm kiếm thành công");
			request.setAttribute("lst", lst);
			request.getRequestDispatcher("views/formKHT.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Tìm kiếm thất bại");
			request.setAttribute("lst", lst);
			request.getRequestDispatcher("views/formKHT.jsp").forward(request, response);
		}
	}

}
