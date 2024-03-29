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
	public static List<Competition> readCompetitionSheets(XSSFWorkbook wb) {
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
						currentComp = new Competition(row.getCell(2).getStringCellValue(),
								row.getCell(1).getStringCellValue());
					} else if (c0.equalsIgnoreCase("competition link")) {
						currentComp.setLink(row.getCell(1).getStringCellValue());
					} else if (c0.equalsIgnoreCase("competition date")) {
						currentComp.setDate(row.getCell(1).getDateCellValue());
					}
					break;
				case BLANK:
					if (currentTeam != null)
						currentComp.addTeam(currentTeam);
					currentTeam = new Team(formatter.formatCellValue(row.getCell(5)));
					if(row.getCell(6)!= null && row.getCell(6).getBooleanCellValue())
						currentTeam.setWinner();
					break;
				case NUMERIC:
					currentTeam.addStudent(
							new Student(row.getCell(2).getStringCellValue(), formatter.formatCellValue(row.getCell(1)),
									row.getCell(3).getStringCellValue()));
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

	public static void rewrite(XSSFWorkbook wb,List<Competition> competitions) {
		Utils.loop(wb.getNumberOfSheets(), i -> wb.removeSheetAt(0));
		competitions.forEach(comp -> ExcelUtil.writeCompetitionSheet(comp, wb));
		
	}

	public static void deleteCompetitionSheet(Competition comp, XSSFWorkbook wb) {
		wb.removeSheetAt(wb.getSheetIndex(comp.getId()));
	}

	public synchronized static void writeCompetitionSheet(Competition comp, XSSFWorkbook wb) {
		if (wb.getSheetIndex(comp.getId()) != -1) {
			deleteCompetitionSheet(comp, wb);
		}
		XSSFSheet sheet = wb.createSheet(comp.getId());
		sheet.createRow(0).createCell(0).setCellValue("Competition Name");
		sheet.getRow(0).createCell(1).setCellValue(comp.getName());
		sheet.getRow(0).createCell(2).setCellValue(comp.getId());

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
			sheet.getRow(curRow).createCell(5).setCellValue(team.getName());
			sheet.getRow(curRow).createCell(6).setCellValue(team.isWinner());
			int num = 1;
			for (Student student : team.getStudents()) {
				curRow++;
				sheet.createRow(curRow).createCell(0).setCellValue(num);
				sheet.getRow(curRow).createCell(1).setCellValue(student.getStId());
				sheet.getRow(curRow).createCell(2).setCellValue(student.getName());
				sheet.getRow(curRow).createCell(3).setCellValue(student.getMajor());
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
