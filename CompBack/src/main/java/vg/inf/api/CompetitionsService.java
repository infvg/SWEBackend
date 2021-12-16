package vg.inf.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import vg.inf.CompetitionsBackendApplication;
import vg.inf.util.Competition;
import vg.inf.util.ExcelUtil;
import vg.inf.util.Team;

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

		competitions = ExcelUtil.readCompetitionSheets(wb);
	}
	@PreDestroy
	public void destroy() {
		try {
			ExcelUtil.rewrite(wb,competitions);
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeCompetition(String id) {
		Iterator<Competition> it = competitions.iterator();
		while (it.hasNext()) {
			Competition competition = it.next();
			if (competition.getId().equals(id)) {
				competitions.remove(competition);
				return;
			}
		}
	}

	public Competition getCompetition(String id) {
		Iterator<Competition> it = competitions.iterator();
		while (it.hasNext()) {
			Competition competition = it.next();
			if (competition.getId().equals(id)) {
				return competition;
			}
		}
		return null;
	}

	public Competition createAndReturn(String name, String link, Date date) {
		Competition comp = new Competition(name, link, date);
		competitions.add(comp);
		ExcelUtil.writeCompetitionSheet(comp, wb);
		return comp;
	}

	public void admodCompetition(Competition comp) {
		if (getCompetition(comp.getId()) != null)
			competitions.remove(getCompetition(comp.getId()));
		competitions.add(comp);
		ExcelUtil.writeCompetitionSheet(comp, wb);
	}

	public void removeCompetition(Competition competition) {
		competitions.remove(competition);
		ExcelUtil.deleteCompetitionSheet(competition, wb);
	}

	public List<Competition> getAll() {
		return competitions;
	}
}