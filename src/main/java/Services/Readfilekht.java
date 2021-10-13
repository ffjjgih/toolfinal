package Services;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.DsThi;
import Model.KiHoc;

public class Readfilekht {

	private ArrayList<DsThi> lst;

	public Readfilekht() {
		this.lst = new ArrayList<>();
	}

	public ArrayList<DsThi> read(InputStream namefile, KiHoc k) {
		List<Integer> dscolumndiem = new ArrayList<>();
		String loaithi, block, ki, lop, tenmon, giangvien;
		try {
			String mamon, mamonhoc, phongthi;

			java.util.Date ngay = null;
			String ngaythi;
			int cathi;
			XSSFWorkbook workbook = new XSSFWorkbook(namefile);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			sheet.getRow(1).forEach(cellkht -> {
				if (cellkht.getStringCellValue().equalsIgnoreCase("ngày thi")
						|| cellkht.getStringCellValue().equalsIgnoreCase("Ca")
						|| cellkht.getStringCellValue().equalsIgnoreCase("phòng thi")
						|| cellkht.getStringCellValue().equalsIgnoreCase("mã môn")
						|| cellkht.getStringCellValue().equalsIgnoreCase("lớp")
						|| cellkht.getStringCellValue().equalsIgnoreCase("loại thi")
						|| cellkht.getStringCellValue().equalsIgnoreCase("tên môn")) {
					dscolumndiem.add(cellkht.getColumnIndex());

				}
			});
			while (iterator.hasNext()) {
				Row row = iterator.next();
				ki = sheet.getRow(0).getCell(0).getStringCellValue();
				if (row.getRowNum() > 3) {
					if (row.getCell(6).getCellType() == CellType.STRING) {
						mamonhoc = row.getCell(6).getStringCellValue();
					} else {
						mamonhoc = row.getCell(6).getNumericCellValue() + "";
					}
					phongthi = row.getCell(3).getStringCellValue();
					ngay = row.getCell(1).getDateCellValue();
					cathi = (int) row.getCell(2).getNumericCellValue();
					loaithi = row.getCell(8).getStringCellValue();
					tenmon = row.getCell(5).getStringCellValue();
					lop = row.getCell(10).getStringCellValue();

					giangvien = row.getCell(11).getStringCellValue();
					if (mamonhoc.length() > 0 && phongthi.length() > 0 && cathi > 0) {
						lop = lop.replaceAll(" ", "");
						mamonhoc = mamonhoc.replaceAll(" ", "");
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
						ngaythi = format.format(ngay);
						lst.add(new DsThi(cathi, giangvien, loaithi, lop, mamonhoc, ngaythi, phongthi, tenmon, k));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.lst;
	}
}
