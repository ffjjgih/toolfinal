package Services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.SinhVien;

public class Readfilediemdanh {
	private ArrayList<SinhVien> listSV;
	private ArrayList<SinhVien> listThi;
	private ArrayList<SinhVien> listSVDiemDanhFile;
	private ArrayList<SinhVien> lstsvcamthi;
	private SinhVien sv;
	private Cell cell;
	public Readfilediemdanh() {
		this.sv = new SinhVien();
		this.listSV = new ArrayList<>();
		this.listThi = new ArrayList<>();
		this.listSVDiemDanhFile = new ArrayList<>();
		this.lstsvcamthi=new ArrayList<SinhVien>();
	}
	public ArrayList<SinhVien> readDiemDanhFile(Iterator<Row> iteratorRow, List<Integer> listCell) {
		String lop=null, mamon=null;
		while (iteratorRow.hasNext()) {
			Row row = iteratorRow.next();
			this.sv = new SinhVien();
			Iterator<Cell> iteratorCell = row.cellIterator();
			if (row.getRowNum() == 2) {
				lop = row.getCell(3).getStringCellValue();
				lop= lop.replaceAll(" ", "");
			}
			if (row.getRowNum() == 3) {
				mamon = row.getCell(3).getStringCellValue();
				mamon= mamon.replaceAll(" ", "");
			}
			if (row.getRowNum() > 7) {
				while (iteratorCell.hasNext()) {
					this.cell = iteratorCell.next();
					if (cell.getColumnIndex() == listCell.get(0)) {
						this.sv.setIdSV(row.getCell(listCell.get(0)).getStringCellValue());
					}
					if (cell.getColumnIndex() == listCell.get(1)) {
						this.sv.setNameSV(row.getCell(listCell.get(1)).getStringCellValue());
					}
					if (cell.getColumnIndex() == listCell.get(2)) {
						this.sv.setStatus(row.getCell(listCell.get(2)).getStringCellValue());
					}
				}
				this.sv.setMamon(mamon);
				this.sv.setLop(lop);
				this.sv.setMark(0);
				this.listSVDiemDanhFile.add(sv);
			}
		}
		return listSVDiemDanhFile;
	}

	public Integer kiemTra(InputStream nameFile) throws IOException {
		List<Integer> listColumn = new ArrayList<>();
		XSSFSheet sheet = this.createSheet(nameFile);
		Iterator<Row> iterator = this.createIterator(sheet);
		sheet.getRow(6).forEach(cellmassv -> {
			if (cellmassv.getStringCellValue().equalsIgnoreCase("MSSV")){
				listColumn.add(cellmassv.getColumnIndex());
			}
		});
		sheet.getRow(6).forEach(Cellname -> {
			if ( Cellname.getStringCellValue().equalsIgnoreCase("Họ Và Tên")) {
				listColumn.add(Cellname.getColumnIndex());
			}
		});
		sheet.getRow(6).forEach(Cellstatus -> {
			if (Cellstatus.getStringCellValue().equalsIgnoreCase("Trạng Thái")) {
				listColumn.add(Cellstatus.getColumnIndex());
			}
		});
		try {
			listSV = this.readDiemDanhFile(iterator, listColumn);
			this.checkDiemDanh(listSV);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listColumn.size();
	}

	private XSSFSheet createSheet(InputStream nameFile) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(nameFile);
		XSSFSheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	private Iterator createIterator(XSSFSheet sheet) {
		Iterator<Row> iterator = sheet.iterator();
		return iterator;
	}

	private void checkDiemDanh(ArrayList<SinhVien> lstSV) {
		if (lstSV == null) {

		} else {
			for (int i = 0; i < lstSV.size(); i++) {
				if (!lstSV.get(i).getStatus().equalsIgnoreCase("Attendance failed")) {
					this.listThi.add(new SinhVien(lstSV.get(i).getIdSV(), lstSV.get(i).getNameSV(),
							lstSV.get(i).getStatus(), lstSV.get(i).getMark(),lstSV.get(i).getMamon(),lstSV.get(i).getLop()));
				} else {
					this.lstsvcamthi.add(new SinhVien(lstSV.get(i).getIdSV(), lstSV.get(i).getNameSV(),
							lstSV.get(i).getStatus(), lstSV.get(i).getMark(),lstSV.get(i).getMamon(),lstSV.get(i).getLop()));
				}
			}
		}
	}
	
	public ArrayList<SinhVien> xuatsvthi(){
		return this.listThi;
	}
	
	public ArrayList<SinhVien> xuatsvcamthi(){
		return this.lstsvcamthi;
	}
}
