package Services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.SinhVien;

public class Readfileonline {
	private ArrayList<SinhVien> listSinhVien;
	private ArrayList<SinhVien> listSVThi;
	private ArrayList<SinhVien> ListSVcamthi;
	private SinhVien sv;
	private String lop, mamon;
	private Cell cell;

	public Readfileonline() {
		this.listSinhVien = new ArrayList<>();
		this.listSVThi = new ArrayList<>();
		this.ListSVcamthi = new ArrayList<>();
		this.sv = new SinhVien();
	}

	public Integer kiemTra(InputStream fileName) throws IOException {
		List<Integer> listColumn = new ArrayList<>();
		Sheet sheet = this.createSheet(fileName);
		Iterator<Row> iterator = this.createIterator(sheet);
		sheet.getRow(6).forEach(cellHeader -> {
			if (cellHeader.getStringCellValue().equalsIgnoreCase("Bài Học Online")) {
				sheet.getRow(6).forEach(Cellmssv -> {
					if (Cellmssv.getStringCellValue().equalsIgnoreCase("MSSV")) {
						listColumn.add(Cellmssv.getColumnIndex());
					}
				});
				sheet.getRow(6).forEach(cellname -> {
					if (cellname.getStringCellValue().equalsIgnoreCase("Họ Và Tên")) {
						listColumn.add(cellname.getColumnIndex());
					}
				});
				sheet.getRow(6).forEach(cellonline -> {
					if (cellonline.getStringCellValue().equalsIgnoreCase("Bài Học Online")) {
						listColumn.add(cellonline.getColumnIndex());
					}
				});
				sheet.getRow(6).forEach(cellstatus -> {
					if (cellstatus.getStringCellValue().equalsIgnoreCase("Trạng Thái")) {
						listColumn.add(cellstatus.getColumnIndex());
					}
				});
				try {
					listSinhVien = this.docDiemOnline(iterator, listColumn);
					this.checkDiemOnl(listSinhVien);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return listColumn.size();
	}

	public ArrayList<SinhVien> docDiemOnline(Iterator<Row> iteratorRow, List<Integer> listCell) {
		ArrayList<SinhVien> listSV = new ArrayList<>();
		try {
			while (iteratorRow.hasNext()) {
				Row row = iteratorRow.next();
				this.sv = new SinhVien();
				Iterator<Cell> iteratorCell = row.cellIterator();
				if (row.getRowNum() == 2) {
					this.lop = row.getCell(3).getStringCellValue();
					lop = lop.replaceAll(" ", "");
				}
				if (row.getRowNum() == 3) {
					this.mamon = row.getCell(3).getStringCellValue();
					mamon = mamon.replaceAll(" ", "");
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
							this.sv.setMark((double) row.getCell(listCell.get(2)).getNumericCellValue());
						}
						if (cell.getColumnIndex() == listCell.get(3)) {
							this.sv.setStatus(row.getCell(listCell.get(3)).getStringCellValue());
						}
					}
					this.sv.setMamon(mamon);
					this.sv.setLop(lop);
					listSV.add(sv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSV;
	}

	public void checkDiemOnl(ArrayList<SinhVien> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMark() > 7.5 && !list.get(i).getStatus().equalsIgnoreCase("Attendance failed")) {
				this.listSVThi.add(new SinhVien(list.get(i).getIdSV(), list.get(i).getNameSV(), list.get(i).getStatus(),
						list.get(i).getMark(), list.get(i).getMamon(), list.get(i).getLop()));
			} else {
				this.ListSVcamthi.add(new SinhVien(list.get(i).getIdSV(), list.get(i).getNameSV(),
						list.get(i).getStatus(), list.get(i).getMark(), list.get(i).getMamon(), list.get(i).getLop()));
			}
		}
	}

	private Sheet createSheet(InputStream nameFile) throws IOException {
		File f = new File("Summer2021-COM108(1).xlsx");
		Workbook workbook = new XSSFWorkbook(nameFile);
		Sheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	private Iterator createIterator(Sheet sheet) {
		Iterator<Row> iterator = sheet.iterator();
		return iterator;
	}

	public ArrayList<SinhVien> xuatsvthi() {
		return this.listSVThi;
	}

	public ArrayList<SinhVien> xuatsvcamthi() {
		return this.ListSVcamthi;
	}
}
