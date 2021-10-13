package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import Dao.Daokht;
import Dao.Daokithi;
import Model.DsThi;
import Model.KiHoc;

@WebServlet("/updatekht")
public class UpdateKHTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Daokht dao;
	private DsThi kht;
	private Daokithi daokithi;
	private int index;
	
    public UpdateKHTController() {
        super();
        this.dao = new Daokht();
        this.kht = new DsThi();
        this.daokithi=new Daokithi();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		index = Integer.parseInt(request.getParameter("id"));
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("index");
		int idkh = Integer.parseInt(id);
		DsThi kht = this.dao.getKHTById(idkh);
		if (kht != null) {
			request.setAttribute("detail", kht);
			request.getRequestDispatcher("views/loadToUpdate.jsp").forward(request, response);
			request.setAttribute("suc", "GET kế Hoạch THI SUCCESSFUL!");
		} else {
			request.setAttribute("fail", "GET FAIL, PLEASE TRY AGAIN!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		try {
			BeanUtils.populate(this.kht, request.getParameterMap());
			this.kht.setId(Integer.parseInt(request.getParameter("idkht")));
			KiHoc k=this.daokithi.findkihoc(index);
			this.kht.setKiHoc(k);
			  this.kht.setNgayThi(request.getParameter("ngay"));
			 
			this.dao.update(kht);
			request.setAttribute("suc", "UPDATE SUCCESSFUL!");
			response.sendRedirect("http://localhost:8080/Toolpdt/Updatekihoc?id=" + index);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "INSERT FAIL, TRY AGAIN!");
			request.getRequestDispatcher("views/ErrorForm.jsp").forward(request, response);
		}
	}
}
