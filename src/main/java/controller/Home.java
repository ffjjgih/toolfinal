package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.Daokithi;
import Model.KiHoc;

@WebServlet({
	"/Home",
	"/Home/insert",
	"/Home/logout"
})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private KiHoc k;
    private Daokithi kithi;
    private List<KiHoc> lstkh;
    
    public Home() {
        super();
        this.k=new KiHoc();
        this.kithi=new Daokithi();
        this.lstkh=new ArrayList<KiHoc>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			updatekihoc(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.lstkh=this.kithi.getkihoc();
		request.setAttribute("lstkythi", lstkh);
		request.getRequestDispatcher("/views/HomeForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String url=request.getRequestURL().toString();
		if(url.contains("insert")) {
			insert(request, response);
			KiHoc k1=this.kithi.getkihoc().get(0);
			response.sendRedirect("http://localhost:8080/Toolpdt/Uploadkht?id="+k1.getIdhk());
		}else if(url.contains("logout")) {
			HttpSession session = request.getSession();
			session.setAttribute("user1", null);
			response.sendRedirect("http://localhost:8080/Toolpdt/login-google");
			return;
		}
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) {
			LocalDate datetime = LocalDate.now();
			java.sql.Date datetimesql = java.sql.Date.valueOf(datetime);
			k.setBlockid(request.getParameter("BLOCKID"));
			k.setNgayTao(datetimesql);
			k.setTrangThai("Đang Hoạt động");
			k.setKyHoc(request.getParameter("kyhoc"));
			k.setNamHoc(request.getParameter("nam_hoc"));
			this.kithi.insert(k);
		
	}
	
	private void updatekihoc(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        LocalDate ngay2=java.time.LocalDate.now();
        Date ngay3=dateFormat.parse(ngay2.toString());
        this.lstkh=this.kithi.getkihoc();
        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        for(KiHoc x:lstkh) {
        	c1.setTime(x.getNgayTao());
            c2.setTime(ngay3);
            long ngay=(c2.getTime().getTime()-c1.getTime().getTime())/(24*3600*1000);
            if(ngay>180){
                this.kithi.updatekihoc(x.getIdhk());
            }
        }
        
	}
}
