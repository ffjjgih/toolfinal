package Services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.SinhVien;

public class ReadFileQuiz {
	private ArrayList<SinhVien> listSinhVien;
	private ArrayList<SinhVien> listSVThi;
	private ArrayList<SinhVien> listSVCamThi;
	private SinhVien sv;
	private Cell cell;
	private String lop, mamon;

	public ReadFileQuiz() {
		this.listSinhVien = new ArrayList<SinhVien>();
		this.listSVThi = new ArrayList<SinhVien>();
		this.listSVCamThi = new ArrayList<SinhVien>();
	}

	public Integer kiemTraQuiz(InputStream fileName) throws IOException {
		ArrayList<Integer> listColumn = new ArrayList<Integer>();
		XSSFSheet sheet = this.createSheet(fileName);
		Iterator<Row> iteratorRow = this.createIterator(sheet);

		for (Cell x : sheet.getRow(6)) {
			if (x.getStringCellValue().equalsIgnoreCase("Quiz online 1")
					|| x.getStringCellValue().equalsIgnoreCase("Quiz online 2")
					|| x.getStringCellValue().equalsIgnoreCase("Quiz online 3")
					|| x.getStringCellValue().equalsIgnoreCase("Quiz online 4")
					|| x.getStringCellValue().equalsIgnoreCase("Quiz online 5")
					|| x.getStringCellValue().equalsIgnoreCase("Quiz online 6")
					|| x.getStringCellValue().equalsIgnoreCase("Quiz online 7")
					|| x.getStringCellValue().equalsIgnoreCase("Quiz online 8")) {
				for (Cell y : sheet.getRow(6)) {
					if (y.getStringCellValue().equalsIgnoreCase("MSSV")
							|| y.getStringCellValue().equalsIgnoreCase("Họ Và Tên")
							|| y.getStringCellValue().equalsIgnoreCase("Quiz online 1")
							|| y.getStringCellValue().equalsIgnoreCase("Quiz online 2")
							|| y.getStringCellValue().equalsIgnoreCase("Quiz online 3")
							|| y.getStringCellValue().equalsIgnoreCase("Quiz online 4")
							|| y.getStringCellValue().equalsIgnoreCase("Quiz online 5")
							|| y.getStringCellValue().equalsIgnoreCase("Quiz online 6")
							|| y.getStringCellValue().equalsIgnoreCase("Quiz online 7")
							|| y.getStringCellValue().equalsIgnoreCase("Quiz online 8")
							|| y.getStringCellValue().equalsIgnoreCase("Trạng Thái")) {
						listColumn.add(y.getColumnIndex());
					}
				}
				try {
					this.listSinhVien = this.readDiemQuiz(iteratorRow, listColumn);
					this.checkDieuKien(listSinhVien);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return listColumn.size();
	}

	private ArrayList<SinhVien> readDiemQuiz(Iterator<Row> iterator, ArrayList<Integer> listInteger) {
		double mark = 0;
		ArrayList<SinhVien> lstSV = new ArrayList<SinhVien>();
		try {
			while (iterator.hasNext()) {
				Row row = iterator.next();
				this.sv = new SinhVien();
				Iterator<Cell> iteratorCell = row.cellIterator();
				if (row.getRowNum() == 2) {
					lop = row.getCell(3).getStringCellValue();
				}
				if (row.getRowNum() == 3) {
					mamon = row.getCell(3).getStringCellValue();
				}
				if (row.getRowNum() > 7) {
					while (iteratorCell.hasNext()) {
						this.cell = iteratorCell.next();
						if (cell.getColumnIndex() == listInteger.get(10)) {
							this.sv.setStatus(row.getCell(listInteger.get(10)).getStringCellValue());
						}
						if (cell.getColumnIndex() == listInteger.get(0)) {
							this.sv.setIdSV(row.getCell(listInteger.get(0)).getStringCellValue());
						}
						if (cell.getColumnIndex() == listInteger.get(1)) {
							this.sv.setNameSV(row.getCell(listInteger.get(1)).getStringCellValue());
						}
						if (cell.getColumnIndex() == listInteger.get(2)) {
							mark += row.getCell(listInteger.get(2)).getNumericCellValue();
						}
						if (cell.getColumnIndex() == listInteger.get(3)) {
							mark += row.getCell(listInteger.get(3)).getNumericCellValue();
						}
						if (cell.getColumnIndex() == listInteger.get(4)) {
							mark += row.getCell(listInteger.get(4)).getNumericCellValue();
						}
						if (cell.getColumnIndex() == listInteger.get(5)) {
							mark += row.getCell(listInteger.get(5)).getNumericCellValue();
						}
						if (cell.getColumnIndex() == listInteger.get(6)) {
							mark += row.getCell(listInteger.get(6)).getNumericCellValue();
						}
						if (cell.getColumnIndex() == listInteger.get(7)) {
							mark += row.getCell(listInteger.get(7)).getNumericCellValue();
						}
						if (cell.getColumnIndex() == listInteger.get(8)) {
							mark += row.getCell(listInteger.get(8)).getNumericCellValue();
						}
						if (cell.getColumnIndex() == listInteger.get(9)) {
							mark += row.getCell(listInteger.get(9)).getNumericCellValue();
						}
					}
					this.sv.setMamon(mamon);
					this.sv.setLop(lop);
					this.sv.setMark(mark);
					lstSV.add(sv);
					mark = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstSV;
	}

	private void checkDieuKien(ArrayList<SinhVien> listCheck) {
		for (int i = 0; i < listCheck.size(); i++) {
			if (listCheck.get(i).getMark() < 100
					&& listCheck.get(i).getStatus().equalsIgnoreCase("Attendance failed")) {
				this.listSVCamThi.add(new SinhVien(listCheck.get(i).getIdSV(), listCheck.get(i).getNameSV(),
						listCheck.get(i).getStatus(), listCheck.get(i).getMark(), listCheck.get(i).getMamon(),
						listCheck.get(i).getLop()));
			} else {
				this.listSVThi.add(new SinhVien(listCheck.get(i).getIdSV(), listCheck.get(i).getNameSV(),
						listCheck.get(i).getStatus(), listCheck.get(i).getMark(), listCheck.get(i).getMamon(),
						listCheck.get(i).getLop()));
			}
		}
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

	public ArrayList<SinhVien> getListSinhVienCamThi() {
		return this.listSVCamThi;
	}

	public ArrayList<SinhVien> getListSinhVienDiThi() {
		return this.listSVThi;
	}
}
