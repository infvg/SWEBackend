import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reader {
	public static LinkedList<Competition> competitions;

	public static void main(String args[]) throws IOException {
		competitions = new LinkedList<>();
		FileInputStream fis = new FileInputStream(new File("competitions.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		Team currentTeam = null;
		Competition currentComp = null;
		DataFormatter formatter = new DataFormatter();
		int id = 0;
		for (Row row : sheet) {
			switch (row.getCell(0).getCellTypeEnum()) {
			case STRING:
				String c0 = row.getCell(0).getStringCellValue();
				if (c0.equalsIgnoreCase("competition name")) {
					if (currentComp != null)
						competitions.add(currentComp);
					currentComp = new Competition(row.getCell(1).getStringCellValue());
				} else if (c0.equalsIgnoreCase("competition link")) {
					currentComp.link = row.getCell(1).getStringCellValue();
				} else if (c0.equalsIgnoreCase("competition date")) {
					currentComp.date = row.getCell(1).getDateCellValue();
				}
				break;
			case BLANK:
				if (currentTeam != null)
					currentComp.teams.add(currentTeam);
				currentTeam = new Team(id);
				id++;
				break;
			case NUMERIC:
				currentTeam.students
						.add(new Student(row.getCell(2).getStringCellValue(), formatter.formatCellValue(row.getCell(1)),
								row.getCell(3).getStringCellValue(), formatter.formatCellValue(row.getCell(4))));
			default:
				break;
			}
		}
		if (currentTeam != null)
			currentComp.teams.add(currentTeam);
		System.out.println(String.format("%s %s %s", currentComp.name, currentComp.link, currentComp.date.toString()));
		for (Team team : currentComp.teams) {
			for (Student student : team.students) {
				System.out.println(String.format("%s %s %s %s", student.name, student.id, student.major, student.rank));
			}
		}
		wb.close();
	}

}