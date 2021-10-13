package Services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import Model.DsThi;
import Model.SinhVien;

public class Uploaddsthi {

	private ArrayList<SinhVien> lstsvthi;
	private ArrayList<SinhVien> lstsvcamthi;
	private List<DsThi> lst;

	public Uploaddsthi() {
		this.lstsvthi = new ArrayList<SinhVien>();
		this.lstsvcamthi = new ArrayList<SinhVien>();
		this.lst = new ArrayList<DsThi>();
	}

	public void xuatlichthi(List<DsThi> lstkht, ArrayList<SinhVien> svthi, ArrayList<SinhVien> svcthi,
			HttpServletResponse response) throws Exception {
		int countcathi = socathi();
		this.lst = lstkht;
		this.lstsvthi = svthi;
		this.lstsvcamthi = svcthi;
		int ca1 = cathi1(lstsvthi), ca2 = cathi2(lstsvthi), ca3 = cathi3(lstsvthi);
		String fileName = "dsthi-" + this.lstsvthi.get(0).getMamon() + "-" + this.lstsvthi.get(0).getLop() + ".xlsx";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		ServletOutputStream pos = response.getOutputStream();
		XSSFWorkbook xssfw = new XSSFWorkbook();
		XSSFRow row, row1, row2, row3, row4, row5, row6;
		XSSFCell cellB, cellC, cellD, cellE, cellF, cellG, cellH;
		ArrayList<SinhVien> lstsvkdt = new ArrayList<>();
		lstsvkdt.addAll(svcthi);
		XSSFSheet sheet = xssfw.createSheet(lst.get(0).getNgayThi().toString());
		for (int j = 0; j <= countcathi; j++) {
			if (j < countcathi && j > 0) {
				sheet = xssfw.createSheet(lstkht.get(j).getNgayThi().toString());
			}
			if (countcathi == j) {
				sheet = xssfw.createSheet("Cấm thi");
			}
			XSSFCellStyle style = xssfw.createCellStyle();
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderBottom(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			sheet.setColumnWidth(2, 6000);
			sheet.setColumnWidth(3, 3000);
			sheet.setColumnWidth(0, 1000);
			row1 = sheet.createRow((short) 0);
			row2 = sheet.createRow((short) 1);
			row3 = sheet.createRow((short) 2);
			row4 = sheet.createRow((short) 3);
			row5 = sheet.createRow((short) 4);
			row6 = sheet.createRow((short) 6);

			cellB = row6.createCell((short) 0);
			cellB.setCellStyle(style);
			cellB.setCellValue("TT");
			cellC = row6.createCell((short) 1);
			cellC.setCellStyle(style);
			cellC.setCellValue("MSSSV");
			cellD = row6.createCell((short) 2);
			cellD.setCellStyle(style);
			cellD.setCellValue("Họ và Tên");
			cellE = row6.createCell((short) 3);
			cellE.setCellStyle(style);
			cellE.setCellValue("Lớp");
			cellF = row6.createCell((short) 4);
			cellF.setCellStyle(style);
			cellF.setCellValue("Kí tên");
			cellG = row6.createCell((short) 5);
			cellG.setCellStyle(style);
			cellG.setCellValue("Điểm");
			cellH = row6.createCell((short) 6);
			cellH.setCellStyle(style);
			cellH.setCellValue("Ghi chú");
			cellB = row1.createCell((short) 3);
			cellB.setCellValue("Danh Sách Sinh Viên Thi");
			cellB = row2.createCell((short) 3);
			cellB.setCellValue("Kỳ:" + lst.get(0).getKiHoc().getBlockid() + "-" + lst.get(1).getKiHoc().getKyHoc()
					+ lst.get(1).getKiHoc().getNamHoc());
			cellB = row3.createCell((short) 3);
			cellB.setCellValue(
					"Môn thi:" + this.lst.get(0).getTenMon() + " " + "(" + this.lstsvthi.get(0).getMamon() + ")");
			cellB = row4.createCell((short) 3);
			if (j < countcathi) {
				cellB.setCellValue("Phòng Thi:" + lst.get(j).getPhongThi());
				cellB = row5.createCell((short) 0);
				cellB.setCellValue("Ngày Thi: " + lst.get(j).getNgayThi());
				cellC = row5.createCell((short) 3);
				cellC.setCellValue("Ca Thi: " + lst.get(j).getGioThi());
				cellD = row5.createCell((short) 6);
				cellD.setCellValue("Lần Thi: " + lst.get(j).getLoaiThi());
			}
			if (j < countcathi) {
				if (j == 0) {
					for (int i = 1; i <= ca1; i++) {
						row = sheet.createRow((short) i + 6);
						cellB = row.createCell((short) 0);
						cellB.setCellStyle(style);
						cellB.setCellValue(i);
						cellC = row.createCell((short) 1);
						cellC.setCellStyle(style);
						cellC.setCellValue(lstsvthi.get(i - 1).getIdSV());
						cellD = row.createCell((short) 2);
						cellD.setCellStyle(style);
						cellD.setCellValue(lstsvthi.get(i - 1).getNameSV());
						cellE = row.createCell((short) 3);
						cellE.setCellStyle(style);
						cellE.setCellValue(lst.get(0).getLop().replaceAll("\\s+", ""));
						cellF = row.createCell((short) 4);
						cellF.setCellStyle(style);
						cellG = row.createCell((short) 5);
						cellG.setCellStyle(style);
						cellH = row.createCell((short) 6);
						cellH.setCellStyle(style);
					}
				} else if (j == 1) {
					for (int i = 1; i <= ca2; i++) {
						row = sheet.createRow((short) i + 6);
						cellB = row.createCell((short) 0);
						cellB.setCellStyle(style);
						cellB.setCellValue(i);
						cellC = row.createCell((short) 1);
						cellC.setCellStyle(style);
						cellC.setCellValue(lstsvthi.get(i + ca1 - 1).getIdSV());
						cellD = row.createCell((short) 2);
						cellD.setCellStyle(style);
						cellD.setCellValue(lstsvthi.get(i + ca1 - 1).getNameSV());
						cellE = row.createCell((short) 3);
						cellE.setCellStyle(style);
						cellE.setCellValue(lst.get(0).getLop().replaceAll("\\s+", ""));
						cellF = row.createCell((short) 4);
						cellF.setCellStyle(style);
						cellG = row.createCell((short) 5);
						cellG.setCellStyle(style);
						cellH = row.createCell((short) 6);
						cellH.setCellStyle(style);
					}
				} else {
					for (int i = 1; i <= ca3; i++) {
						row = sheet.createRow((short) i + 6);
						cellB = row.createCell((short) 0);
						cellB.setCellStyle(style);
						cellB.setCellValue(i);
						cellC = row.createCell((short) 1);
						cellC.setCellStyle(style);
						cellC.setCellValue(lstsvthi.get(i + ca1 + ca2 - 1).getIdSV());
						cellD = row.createCell((short) 2);
						cellD.setCellStyle(style);
						cellD.setCellValue(lstsvthi.get(i + ca1 + ca2 - 1).getNameSV());
						cellE = row.createCell((short) 3);
						cellE.setCellStyle(style);
						cellE.setCellValue(lst.get(0).getLop().replaceAll("\\s+", ""));
						cellF = row.createCell((short) 4);
						cellF.setCellStyle(style);
						cellG = row.createCell((short) 5);
						cellG.setCellStyle(style);
						cellH = row.createCell((short) 6);
						cellH.setCellStyle(style);
					}
				}
			}

			if (countcathi == j) {
				for (int i = 0; i < svcthi.size(); i++) {
					row = sheet.createRow((short) i + 7);
					cellB = row.createCell((short) 0);
					cellB.setCellValue(i + 1);
					cellC = row.createCell((short) 1);
					cellC.setCellValue(this.lstsvcamthi.get(i).getIdSV());
					cellD = row.createCell((short) 2);
					cellD.setCellValue(this.lstsvcamthi.get(i).getNameSV());
					cellE = row.createCell((short) 3);
					cellE.setCellValue(this.lst.get(0).getLop().replaceAll("\\s+", ""));
					cellG = row.createCell((short) 5);
					cellG.setCellValue(this.lstsvcamthi.get(i).getMark());
					cellF = row.createCell((short) 6);
					cellF.setCellValue(this.lstsvcamthi.get(i).getStatus());
				}
			}
		}
		xssfw.write(pos);
		xssfw.close();
		pos.close();
	}

	public void xuatlichthifileword(ArrayList<SinhVien> svthi, HttpServletResponse response) throws Exception {

		lstsvthi = svthi;
		String fileName = "dsthi-" + this.lstsvthi.get(0).getMamon() + "-" + this.lstsvthi.get(0).getLop() + ".docx";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		ServletOutputStream pos = response.getOutputStream();
		XWPFDocument xwpfd = new XWPFDocument();
		XWPFTable table1, table2, table3;
		int ca1 = cathi1(lstsvthi);
		int ca2 = cathi2(lstsvthi);
		int ca3 = cathi3(lstsvthi);
		int count = socathi();
		for (int j = 0; j <= count; j++) {
			XWPFParagraph tille = xwpfd.createParagraph();
			tille.setAlignment(ParagraphAlignment.CENTER);
			String title = "PHIẾU CHẤM THỰC HÀNH/BẢO VỆ ASSIGNMENT CUỐI MÔN HỌC";
			XWPFRun titleRun = tille.createRun();
			titleRun.setBold(true);
			titleRun.setFontFamily("Times New Roman");
			titleRun.setText(title);
			titleRun.setFontSize(14);

			if (j == 0) {
				table1 = xwpfd.createTable(ca1 + 1, 9);
				table1.getRow(0).setHeight(700);
				table1.getRow(0).getCell(0).setWidth("500");
				table1.getRow(0).getCell(1).setWidth("1500");
				table1.getRow(0).getCell(2).setWidth("2500");
				table1.getRow(0).getCell(3).setWidth("1700");
				table1.getRow(0).getCell(4).setWidth("1700");
				table1.getRow(0).getCell(5).setWidth("1700");
				table1.getRow(0).getCell(6).setWidth("1000");
				table1.getRow(0).getCell(7).setWidth("1000");
				table1.getRow(0).getCell(8).setWidth("1500");
				table1.getRow(0).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(3).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(4).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(5).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(6).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(7).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(8).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table1.getRow(0).getCell(0).setText("TT");
				table1.getRow(0).getCell(1).setText("MASV");
				table1.getRow(0).getCell(2).setText("Họ Tên");
				table1.getRow(0).getCell(3).setText("G3");
				table1.getRow(0).getCell(4).setText("G4-G5");
				table1.getRow(0).getCell(5).setText("G6");
				table1.getRow(0).getCell(6).setText("Điểm bảo vệ");
				table1.getRow(0).getCell(7).setText("SV ký nhận");
				table1.getRow(0).getCell(8).setText("Nhận xét");
				System.out.println(ca1+1);
				for (int i = 1; i <= ca1; i++) {
					table1.getRow(i).setHeight(1300);
					table1.getRow(i).getCell(0).setWidth("500");
					table1.getRow(i).getCell(1).setWidth("1500");
					table1.getRow(i).getCell(2).setWidth("2500");
					table1.getRow(i).getCell(3).setWidth("1700");
					table1.getRow(i).getCell(4).setWidth("1700");
					table1.getRow(i).getCell(5).setWidth("1700");
					table1.getRow(i).getCell(6).setWidth("1000");
					table1.getRow(i).getCell(7).setWidth("1000");
					table1.getRow(i).getCell(8).setWidth("1500");
					table1.getRow(i).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table1.getRow(i).getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table1.getRow(i).getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table1.getRow(i).getCell(0).setText(i + "");
					table1.getRow(i).getCell(1).setText(lstsvthi.get(i - 1).getIdSV());
					table1.getRow(i).getCell(2).setText(lstsvthi.get(i - 1).getNameSV());
					table1.getRow(i).getCell(3).setText("");
					table1.getRow(i).getCell(4).setText("");
					table1.getRow(i).getCell(5).setText("");
					table1.getRow(i).getCell(6).setText("");
					table1.getRow(i).getCell(7).setText("");
					table1.getRow(i).getCell(8).setText("");
				}
			} else if (j == 1) {
				table2 = xwpfd.createTable(ca2 + 1, 9);
				table2.getRow(0).setHeight(700);
				table2.getRow(0).getCell(0).setWidth("500");
				table2.getRow(0).getCell(1).setWidth("1500");
				table2.getRow(0).getCell(2).setWidth("2500");
				table2.getRow(0).getCell(3).setWidth("1700");
				table2.getRow(0).getCell(4).setWidth("1700");
				table2.getRow(0).getCell(5).setWidth("1700");
				table2.getRow(0).getCell(6).setWidth("1000");
				table2.getRow(0).getCell(7).setWidth("1000");
				table2.getRow(0).getCell(8).setWidth("1500");
				table2.getRow(0).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(3).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(4).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(5).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(6).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(7).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(8).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table2.getRow(0).getCell(0).setText("TT");
				table2.getRow(0).getCell(1).setText("MASV");
				table2.getRow(0).getCell(2).setText("Họ Tên");
				table2.getRow(0).getCell(3).setText("G3");
				table2.getRow(0).getCell(4).setText("G4-G5");
				table2.getRow(0).getCell(5).setText("G6");
				table2.getRow(0).getCell(6).setText("Điểm bảo vệ");
				table2.getRow(0).getCell(7).setText("SV ký nhận");
				table2.getRow(0).getCell(8).setText("Nhận xét");
				for (int i = 1; i <= ca2; i++) {
					table2.getRow(i).setHeight(1300);
					table2.getRow(i).getCell(0).setWidth("500");
					table2.getRow(i).getCell(1).setWidth("1500");
					table2.getRow(i).getCell(2).setWidth("2500");
					table2.getRow(i).getCell(3).setWidth("1700");
					table2.getRow(i).getCell(4).setWidth("1700");
					table2.getRow(i).getCell(5).setWidth("1700");
					table2.getRow(i).getCell(6).setWidth("1000");
					table2.getRow(i).getCell(7).setWidth("1000");
					table2.getRow(i).getCell(8).setWidth("1500");
					table2.getRow(i).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table2.getRow(i).getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table2.getRow(i).getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table2.getRow(i).getCell(0).setText(i + "");
					table2.getRow(i).getCell(1).setText(lstsvthi.get(ca1 + i - 1).getIdSV());
					table2.getRow(i).getCell(2).setText(lstsvthi.get(ca1 + i - 1).getNameSV());
					table2.getRow(i).getCell(3).setText("");
					table2.getRow(i).getCell(4).setText("");
					table2.getRow(i).getCell(5).setText("");
					table2.getRow(i).getCell(6).setText("");
					table2.getRow(i).getCell(7).setText("");
					table2.getRow(i).getCell(8).setText("");
				}
			} else {
				table3 = xwpfd.createTable(ca3 + 1, 9);
				table3.getRow(0).setHeight(700);
				table3.getRow(0).getCell(0).setWidth("500");
				table3.getRow(0).getCell(1).setWidth("1500");
				table3.getRow(0).getCell(2).setWidth("2500");
				table3.getRow(0).getCell(3).setWidth("1700");
				table3.getRow(0).getCell(4).setWidth("1700");
				table3.getRow(0).getCell(5).setWidth("1700");
				table3.getRow(0).getCell(6).setWidth("1000");
				table3.getRow(0).getCell(7).setWidth("1000");
				table3.getRow(0).getCell(8).setWidth("1500");
				table3.getRow(0).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(3).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(4).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(5).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(6).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(7).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(8).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
				table3.getRow(0).getCell(0).setText("TT");
				table3.getRow(0).getCell(1).setText("MASV");
				table3.getRow(0).getCell(2).setText("Họ Tên");
				table3.getRow(0).getCell(3).setText("G3");
				table3.getRow(0).getCell(4).setText("G4-G5");
				table3.getRow(0).getCell(5).setText("G6");
				table3.getRow(0).getCell(6).setText("Điểm bảo vệ");
				table3.getRow(0).getCell(7).setText("SV ký nhận");
				table3.getRow(0).getCell(8).setText("Nhận xét");
				for (int i = 1; i <= ca3; i++) {
					table3.getRow(i - 1).setHeight(1300);
					table3.getRow(i).getCell(0).setWidth("500");
					table3.getRow(i).getCell(1).setWidth("1500");
					table3.getRow(i).getCell(2).setWidth("2500");
					table3.getRow(i).getCell(3).setWidth("1700");
					table3.getRow(i).getCell(4).setWidth("1700");
					table3.getRow(i).getCell(5).setWidth("1700");
					table3.getRow(i).getCell(6).setWidth("1000");
					table3.getRow(i).getCell(7).setWidth("1000");
					table3.getRow(i).getCell(8).setWidth("1500");
					table3.getRow(i).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table3.getRow(i).getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table3.getRow(i).getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					table3.getRow(i).getCell(0).setText(i + "");
					table3.getRow(i).getCell(1).setText(lstsvthi.get(ca1 + ca2 + i - 1).getIdSV());
					table3.getRow(i).getCell(2).setText(lstsvthi.get(ca1 + ca2 + i - 1).getNameSV());
					table3.getRow(i).getCell(3).setText("");
					table3.getRow(i).getCell(4).setText("");
					table3.getRow(i).getCell(5).setText("");
					table3.getRow(i).getCell(6).setText("");
					table3.getRow(i).getCell(7).setText("");
					table3.getRow(i).getCell(8).setText("");
				}
			}
			tille.setPageBreak(true);
		}
		xwpfd.write(pos);
		xwpfd.close();
		pos.close();
		this.lst.removeAll(lst);
		this.lstsvthi.removeAll(lstsvthi);
		this.lstsvcamthi.removeAll(lstsvcamthi);
	}

	private Integer socathi() {
		if (lstsvthi.size() > 26) {
			return 2;
		} else {
			return 3;
		}
	}

	private Integer cathi1(ArrayList<SinhVien> dsthi) {
		if (dsthi.size() > 26) {
			if (dsthi.size() % 3 == 0) {
				return dsthi.size() / 3;
			} else if (dsthi.size() % 3 == 1) {
				return (dsthi.size() - 1) / 3 + 1;
			} else {
				return (dsthi.size() - 2) / 3 + 1;
			}
		} else {
			if (dsthi.size() % 2 == 0) {
				return dsthi.size() / 2;
			} else {
				return (dsthi.size() - 1) / 2 + 1;
			}
		}
	}

	private Integer cathi2(ArrayList<SinhVien> dsthi) {
		if (dsthi.size() > 26) {
			if (dsthi.size() % 3 == 0) {
				return dsthi.size() / 3;
			} else if (dsthi.size() % 3 == 1) {
				return (dsthi.size() - 1) / 3;
			} else {
				return (dsthi.size() - 2) / 3 + 1;
			}
		} else {
			if (dsthi.size() % 2 == 0) {
				return dsthi.size() / 2;
			} else {
				return (dsthi.size() - 1) / 2;
			}
		}
	}

	private Integer cathi3(ArrayList<SinhVien> dsthi) {
		if (dsthi.size() > 26) {
			if (dsthi.size() % 3 == 0) {
				return dsthi.size() / 3;
			} else if (dsthi.size() % 3 == 1) {
				return (dsthi.size() - 1) / 3;
			} else {
				return (dsthi.size() - 2) / 3;
			}
		} else {
			return 0;
		}
	}
}
