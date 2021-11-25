package vg.inf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import vg.inf.util.Competition;
import vg.inf.util.Student;
import vg.inf.util.Team;

@Service
public class CompetitionsService {

	private static List<Competition> competitions = new ArrayList<>();
	private static long idCounterComp = 0;
	private static long idCounterTeam = 0;
	private static long idCounterStu = 0;

	static {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(new FileInputStream("competitions.xlsx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				switch (row.getCell(0).getCellType()) {
				case STRING:
					String c0 = row.getCell(0).getStringCellValue();
					if (c0.equalsIgnoreCase("competition name")) {
						if (currentComp != null)
							competitions.add(currentComp);
						currentComp = new Competition(++idCounterComp, row.getCell(1).getStringCellValue());
					} else if (c0.equalsIgnoreCase("competition link")) {
						currentComp.setLink(row.getCell(1).getStringCellValue());
					} else if (c0.equalsIgnoreCase("competition date")) {
						currentComp.setDate(row.getCell(1).getDateCellValue());
					}
					break;
				case BLANK:
					if (currentTeam != null)
						currentComp.addTeam(currentTeam);
					currentTeam = new Team(++idCounterTeam, formatter.formatCellValue(row.getCell(5)));
					break;
				case NUMERIC:
					currentTeam.addStudent(new Student(++idCounterStu, row.getCell(2).getStringCellValue(),
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
	}

	public void removeCompetition(long id) {
		Iterator<Competition> it = competitions.iterator();
		while (it.hasNext()) {
			Competition competition = it.next();
			if (competition.getId() == id) {
				competitions.remove(competition);
				return;
			}
		}
	}

	public Competition getCompetition(long id) {
		Iterator<Competition> it = competitions.iterator();
		while (it.hasNext()) {
			Competition competition = it.next();
			if (competition.getId() == id) {
				return competition;
			}
		}
		return null;
	}

	public void removeCompetition(Competition competition) {
		competitions.remove(competition);
	}

	public List<Competition> getAll() {
		return competitions;
	}
}