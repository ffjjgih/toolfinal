package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import Dao.Daokht;
import Dao.Daokithi;
import Model.DsThi;
import Model.KiHoc;
import Services.Readfilekht;

@MultipartConfig()
@WebServlet("/Uploadkht")
public class Uploadkht extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String namefile, tenfile;
	private Readfilekht read;
	private ArrayList<DsThi> lst;
	private List<DsThi> lstkht;
	private KiHoc kihoc;
	private Daokithi dao;
	private Daokht daokht;
	int indexx;

	public Uploadkht() {
		this.read = new Readfilekht();
		this.lst = new ArrayList<DsThi>();
		this.kihoc = new KiHoc();
		this.dao = new Daokithi();
		this.daokht = new Daokht();
		this.lstkht = new ArrayList<DsThi>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		indexx = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("idkihoc", indexx);
		request.getRequestDispatcher("/views/formDoiLichThi.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		request.setAttribute("id", indexx);
		try {
			readfilekht(request, response, indexx);
			
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (IOException er) {
			er.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void readfilekht(HttpServletRequest request, HttpServletResponse response, int index) throws Exception {
		kihoc = this.dao.findid(index);
		Part filePart = request.getPart("namefile");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();
		if (fileName.length() == 0) {
			int error3 = 3;
			this.setValueToSes(request, error3);
			response.sendRedirect("http://localhost:8080/Toolpdt/Uploadkht?id=" + index);
			return;
		} else if (!fileName.contains("xlsx")) {
			response.sendRedirect("http://localhost:8080/Toolpdt/Uploadkht?id=" + index);
			return;
		} else {
			lst = this.read.read(fileContent, kihoc);
			if (lst.size() < 0) {
				int error4 = 4;
				this.setValueToSes(request, error4);
				response.sendRedirect("http://localhost:8080/Toolpdt/Uploadkht?id=" + index);
				return;
			} else
				delete(kihoc, index);
			for (DsThi x : lst) {
				this.daokht.insert(x);
			}
			response.sendRedirect("/Toolpdt/Readlsistmark?id=" + indexx);
		}
	}

	private void delete(KiHoc k, int index) throws Exception {
		this.lstkht = this.daokht.findbykihoc(k);
		this.daokht.deletebykihoc(k);

	}
	
	private void setValueToSes(HttpServletRequest request, int value) {
		HttpSession session = request.getSession();
		session.setAttribute("value", value);
	}
}
