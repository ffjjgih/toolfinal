package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

@WebServlet("/Updatekihoc")
public class Updatekihoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Daokht daokht;
    private Daokithi daokithi;
    private List<DsThi> lstdsthi;
    private KiHoc k;
    private DsThi kht;
    int index;
    
    public Updatekihoc() {
        super();
        this.daokht=new Daokht();
        this.daokithi=new Daokithi();
        this.lstdsthi=new ArrayList<DsThi>();
        this.k=new KiHoc();
        this.kht=new DsThi();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		index=Integer.parseInt(request.getParameter("id"));
		this.k=this.daokithi.findid(index);
		this.lstdsthi=this.daokht.findbykihoc(k);
		request.setAttribute("lst", this.lstdsthi);
		request.setAttribute("idkihoc", index);
		request.getRequestDispatcher("/views/formKHT.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			BeanUtils.populate(this.kht, request.getParameterMap());
			KiHoc k=this.daokithi.findkihoc(index);
			this.kht.setKiHoc(k);
			  this.kht.setNgayThi(request.getParameter("ngay"));
			this.daokht.insert(kht);
			request.setAttribute("suc", "Insert SUCCESSFUL!");
			response.sendRedirect("http://localhost:8080/Toolpdt/Updatekihoc?id=" + index);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "INSERT FAIL, TRY AGAIN!");
			request.getRequestDispatcher("views/ErrorForm.jsp").forward(request, response);
		}
	}

}
