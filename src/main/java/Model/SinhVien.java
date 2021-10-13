package Model;

public class SinhVien {
	private String idSV, nameSV, status,mamon,lop;
	private double mark;
	
	public SinhVien() {

	}

	public SinhVien(String idSV, String nameSV, String status, double mark,String mamon,String lop) {
		this.idSV = idSV;
		this.nameSV = nameSV;
		this.status = status;
		this.mark = mark;
		this.mamon=mamon;
		this.lop=lop;
	}

	public String getIdSV() {
		return idSV;
	}

	public void setIdSV(String idSV) {
		this.idSV = idSV;
	}

	public String getNameSV() {
		return nameSV;
	}

	public void setNameSV(String nameSV) {
		this.nameSV = nameSV;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public void setMamon(String mamon) {
		this.mamon = mamon;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getLop() {
		return lop;
	}
	public String getMamon() {
		return mamon;
	}
	@Override
	public String toString() {
		return "SinhVien [idSV=" + idSV + ", nameSV=" + nameSV + ", status=" + status + ", mark=" + mark + "]";
	}
	
	
}
