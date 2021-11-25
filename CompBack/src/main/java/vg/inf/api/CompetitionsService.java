package vg.inf.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import vg.inf.CompetitionsBackendApplication;
import vg.inf.util.Competition;
import vg.inf.util.ExcelUtil;

@Service
public class CompetitionsService {

	private static List<Competition> competitions = new ArrayList<>();
	private static XSSFWorkbook wb;
	static {
		try {
			wb = new XSSFWorkbook(new FileInputStream(CompetitionsBackendApplication.EXCEL_FILE_INPUT));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		competitions = ExcelUtil.read(wb);
	}

	@PreDestroy
	public void destroy() {
		try {
			ExcelUtil.deleteAllSheets(wb);
			competitions.forEach(comp -> ExcelUtil.write(comp, wb));
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
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