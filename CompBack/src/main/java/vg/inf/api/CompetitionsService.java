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
			ExcelUtil.deleteAllSheets(wb);
			competitions.forEach(comp -> ExcelUtil.writeCompetitionSheet(comp, wb));
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

	public void admodCompetition(Competition comp) {
		if (getCompetition(comp.getId()) != null)
			competitions.remove(getCompetition(comp.getId()));
		competitions.add(comp);
		ExcelUtil.writeCompetitionSheet(comp, wb);
	}

	public void admodTeamToCompetition(String compId, Team team) {
		Competition comp = getCompetition(compId);
		if (comp.getTeam(team.getId()) != null)
			comp.removeTeam(team.getId());
		comp.addTeam(team);
		ExcelUtil.writeCompetitionSheet(comp, wb);
	}

	public void removeTeamFromComp(String compId, Team team) {
		Competition comp = getCompetition(compId);
		if (comp.getTeam(team.getId()) != null)
			comp.removeTeam(team.getId());
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