package vg.inf.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vg.inf.CompetitionsBackendApplication;

public class ExcelUtil {
	public static List<Competition> read(XSSFWorkbook wb) {
		List<Competition> competitions = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			XSSFSheet sheet = wb.getSheetAt(i);
			System.out.println(sheet.getSheetName());
		}
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			XSSFSheet sheet = wb.getSheetAt(i);
			Team currentTeam = null;
			Competition currentComp = null;
			for (Row row : sheet) {
				if (row.getCell(0) == null)
					row.createCell(0).setBlank();
				switch (row.getCell(0).getCellType()) {
				case STRING:
					String c0 = row.getCell(0).getStringCellValue();
					if (c0.equalsIgnoreCase("competition name")) {
						if (currentComp != null)
							competitions.add(currentComp);
						currentComp = new Competition(++Competition.idCounterComp, row.getCell(1).getStringCellValue());
					} else if (c0.equalsIgnoreCase("competition link")) {
						currentComp.setLink(row.getCell(1).getStringCellValue());
					} else if (c0.equalsIgnoreCase("competition date")) {
						currentComp.setDate(row.getCell(1).getDateCellValue());
					}
					break;
				case BLANK:
					if (currentTeam != null)
						currentComp.addTeam(currentTeam);
					currentTeam = new Team(++Team.idCounterTeam, formatter.formatCellValue(row.getCell(5)));
					break;
				case NUMERIC:
					currentTeam.addStudent(new Student(++Student.idCounterStu, row.getCell(2).getStringCellValue(),
							formatter.formatCellValue(row.getCell(1)), row.getCell(3).getStringCellValue(),
							formatter.formatCellValue(row.getCell(4))));
				default:
					break;
				}
			}
			if (currentTeam != null)
				currentComp.addTeam(currentTeam);
			competitions.add(currentComp);
		}
		return competitions;
	}

	public static void deleteAllSheets(XSSFWorkbook wb) {
		Utils.loop(wb.getNumberOfSheets(), i -> wb.removeSheetAt(0));
	}

	public static void write(Competition comp, XSSFWorkbook wb) {
		XSSFSheet sheet = wb.createSheet(comp.getName());
		sheet.createRow(0).createCell(0).setCellValue("Competition Name");
		sheet.getRow(0).createCell(1).setCellValue(comp.getName());

		sheet.createRow(1).createCell(0).setCellValue("Competition Link");
		sheet.getRow(1).createCell(1).setCellValue(comp.getLink());

		sheet.createRow(2).createCell(0).setCellValue("Competition Date");
		CellStyle cellStyle = wb.createCellStyle();
		CreationHelper createHelper = wb.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yy"));
		sheet.getRow(2).createCell(1, CellType.NUMERIC).setCellValue(comp.getDate());
		sheet.getRow(2).getCell(1).setCellStyle(cellStyle);

		int curRow = 3;
		for (Team team : comp.getTeams()) {
			sheet.createRow(curRow).createCell(1).setCellValue("Student ID");
			sheet.getRow(curRow).createCell(0, CellType.BLANK);
			sheet.getRow(curRow).createCell(2).setCellValue("Student Name");
			sheet.getRow(curRow).createCell(3).setCellValue("Major");
			sheet.getRow(curRow).createCell(3 + 1).setCellValue("Rank");
			sheet.getRow(curRow).createCell(5).setCellValue(team.getName());
			int num = 1;
			for (Student student : team.getStudents()) {
				curRow++;
				sheet.createRow(curRow).createCell(0).setCellValue(num);
				sheet.getRow(curRow).createCell(1).setCellValue(student.getId());
				sheet.getRow(curRow).createCell(2).setCellValue(student.getName());
				sheet.getRow(curRow).createCell(3).setCellValue(student.getMajor());
				sheet.getRow(curRow).createCell(3 + 1).setCellValue(student.getRank());
				num++;
			}
			curRow++;
		}

		try {
			wb.write(new FileOutputStream(CompetitionsBackendApplication.EXCEL_FILE_OUTPUT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
