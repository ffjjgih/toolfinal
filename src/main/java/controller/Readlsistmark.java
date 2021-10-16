package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import Model.SinhVien;
import Services.ReadFileQuiz;
import Services.Readfilediemdanh;
import Services.Readfileonline;
import Services.Uploaddsthi;

@MultipartConfig
@WebServlet("/Readlsistmark")
public class Readlsistmark extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Readfilediemdanh diemdanh;
	private Readfileonline online;
	private ArrayList<SinhVien> lstsvthi;
	private ArrayList<SinhVien> lstsvcamthi;
	private ReadFileQuiz quiz;
	private Daokht daokht;
	private Daokithi daokithi;
	private Uploaddsthi uploaddsthi;
	private List<DsThi> lstkht;
	private KiHoc k;
	int index;
	FileInputStream fileInputStream = null;
	OutputStream responseOutputStream = null;

	public Readlsistmark() {
		this.diemdanh = new Readfilediemdanh();
		this.online = new Readfileonline();
		this.lstsvcamthi = new ArrayList<SinhVien>();
		this.lstsvthi = new ArrayList<SinhVien>();
		this.quiz = new ReadFileQuiz();
		this.daokht = new Daokht();
		this.daokithi = new Daokithi();
		this.uploaddsthi = new Uploaddsthi();
		this.lstkht = new ArrayList<DsThi>();
		this.k = new KiHoc();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		index = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("idkihoc", index);
		request.getRequestDispatcher("/views/formUpLoadDiem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Part filePart = request.getPart("namefile");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();
		String loaimon = request.getParameter("Category");
		String loaifile = request.getParameter("downloadFile");
		if (fileName.length() == 0) {
			int valueSes3 = 3;
			this.setValueToSes(request, valueSes3);
			response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
			return;
		} else if (!fileName.contains("xlsx")) {
			int valueSes4 = 4;
			this.setValueToSes(request, valueSes4);
			response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
			return;
		} else {
			if (loaimon.equalsIgnoreCase("option2")) {
				try {
					int countonl=this.online.kiemTra(fileContent);
					if (countonl == 4) {
						this.lstsvcamthi = this.online.xuatsvcamthi();
						this.lstsvthi = this.online.xuatsvthi();
					} else {
						int valueSes5 = 5;
						this.setValueToSes(request, valueSes5);
						response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int valueSes9 = 9;
					this.setValueToSes(request, valueSes9);
					response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
					return;
				}
				
			} else if (loaimon.equalsIgnoreCase("option3")) {
				try {
					int countdd=this.diemdanh.kiemTra(fileContent) ;
					if (countdd == 3) {
						this.lstsvcamthi = this.diemdanh.xuatsvcamthi();
						this.lstsvthi = this.diemdanh.xuatsvthi();
					} else {
						int valueSes6 = 6;
						this.setValueToSes(request, valueSes6);
						response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
						return;
					}
				}catch (Exception e) {
					e.printStackTrace();
					int valueSes9 = 9;
					this.setValueToSes(request, valueSes9);
					response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
					return;
				}
				
			} else {
				try {
					int count=this.quiz.kiemTraQuiz(fileContent);
					if ( count ==88) {
						this.lstsvcamthi = this.quiz.getListSinhVienCamThi();
						this.lstsvthi = this.quiz.getListSinhVienDiThi();
					} else {
						int valueSes7 = 7;
						this.setValueToSes(request, valueSes7);
						response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					int valueSes9 = 9;
					this.setValueToSes(request, valueSes9);
					response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
				}
				
			}
		}
		this.k = this.daokithi.findid(index);
		if (this.lstsvthi.size() != 0) {
			try {
				this.lstkht = this.daokht.findkht(this.lstsvthi.get(0).getMamon(), this.lstsvthi.get(0).getLop(), k);
				if (this.lstkht.size() == 0) {
					int valueSes8 = 8;
					this.setValueToSes(request, valueSes8);
					response.sendRedirect("http://localhost:8080/Toolpdt/Readlsistmark?id=" + index);
					return;

				} else {
					if (loaifile.equalsIgnoreCase("fileExcel")) {
						uploaddsthi.xuatlichthi(this.lstkht, this.lstsvthi, this.lstsvcamthi, response);
					} else {
						uploaddsthi.xuatlichthifileword(lstsvthi, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				int valueSes9 = 9;
				this.setValueToSes(request, valueSes9);
				return;
			}
		} else {
			int valueSes9 = 9;
			this.setValueToSes(request, valueSes9);
			return;
		}
		this.lstsvcamthi.removeAll(lstsvcamthi);
		this.lstsvthi.removeAll(lstsvthi);
		this.lstkht.removeAll(lstkht);
	}
	
	private void setValueToSes(HttpServletRequest request, int value) {
		HttpSession session = request.getSession();
		session.setAttribute("value", value);
	}

}
