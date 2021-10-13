package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ds_thi database table.
 * 
 */
@Entity
@Table(name="ds_thi")
@NamedQuery(name="DsThi.findAll", query="SELECT d FROM DsThi d")
public class DsThi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="CA_THI")
	private int caThi;

	@Column(name="GIANG_VIEN")
	private String giangVien;

	@Column(name="LOAI_THI")
	private String loaiThi;

	private String lop;

	@Column(name="MA_MON")
	private String maMon;

	@Column(name="NGAY_THI")
	private String ngayThi;

	@Column(name="PHONG_THI")
	private String phongThi;

	@Column(name="TEN_MON")
	private String tenMon;

	//bi-directional many-to-one association to KiHoc
	@ManyToOne
	@JoinColumn(name="IDHK")
	private KiHoc kiHoc;

	public DsThi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCaThi() {
		return this.caThi;
	}
	
	public String getGioThi() {
		if(caThi==1) {
			return "7:15-9:15";
		}else if(caThi==2) {
			return "9:25-11:25";
		}else if(caThi==3) {
			return "12:00-14:00";
		}else if(caThi==4) {
			return "14:10-16:10";
		}else if(caThi==5) {
			return "16:20-18:20";
		}else {
			return "18:30-20:30";
		}
	}

	public void setCaThi(int caThi) {
		this.caThi = caThi;
	}

	public String getGiangVien() {
		return this.giangVien;
	}

	public void setGiangVien(String giangVien) {
		this.giangVien = giangVien;
	}

	public String getLoaiThi() {
		return this.loaiThi;
	}

	public void setLoaiThi(String loaiThi) {
		this.loaiThi = loaiThi;
	}

	public String getLop() {
		return this.lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public String getMaMon() {
		return this.maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public String getNgayThi() {
		return this.ngayThi;
	}

	public void setNgayThi(String ngayThi) {
		this.ngayThi = ngayThi;
	}

	public String getPhongThi() {
		return this.phongThi;
	}

	public void setPhongThi(String phongThi) {
		this.phongThi = phongThi;
	}

	public String getTenMon() {
		return this.tenMon;
	}

	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}

	public KiHoc getKiHoc() {
		return this.kiHoc;
	}

	public void setKiHoc(KiHoc kiHoc) {
		this.kiHoc = kiHoc;
	}

	public DsThi(int caThi, String giangVien, String loaiThi, String lop, String maMon, String ngayThi, String phongThi,
			String tenMon, KiHoc kiHoc) {
		super();
		this.caThi = caThi;
		this.giangVien = giangVien;
		this.loaiThi = loaiThi;
		this.lop = lop;
		this.maMon = maMon;
		this.ngayThi = ngayThi;
		this.phongThi = phongThi;
		this.tenMon = tenMon;
		this.kiHoc = kiHoc;
	}

}