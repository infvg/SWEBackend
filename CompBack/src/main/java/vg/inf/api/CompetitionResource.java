package vg.inf.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vg.inf.CompetitionsService;
import vg.inf.util.Competition;
import vg.inf.util.Student;
import vg.inf.util.Team;

@RestController
public class CompetitionResource {

	@Autowired
	private CompetitionsService CompService;

	// Get Requests

	@GetMapping("/competitions")
	public List<Competition> getAllCompetitions() {
		return CompService.getAll();
	}

	@GetMapping("/competitions/{competitionId}")
	public Competition getCompetition(@PathVariable long competitionId) {
		return CompService.getCompetition(competitionId);
	}

	@GetMapping("/competitions/{competitionId}/teams/{teamId}")
	public Team getTeam(@PathVariable long competitionId, @PathVariable long teamId) {
		return CompService.getCompetition(competitionId).getTeam(teamId);
	}

	@GetMapping("/competitions/{competitionId}/teams")
	public List<Team> getTeams(@PathVariable long competitionId) {
		return CompService.getCompetition(competitionId).getTeams();
	}

	@GetMapping("/competitions/{competitionId}/teams/{teamId}/students")
	public List<Student> getStudentsInTeam(@PathVariable long competitionId, @PathVariable long teamId) {
		return CompService.getCompetition(competitionId).getTeam(teamId).getStudents();
	}

	@GetMapping("/competitions/{competitionId}/teams/{teamId}/students/{studentId}")
	public Student getStudent(@PathVariable long competitionId, @PathVariable long teamId,
			@PathVariable long studentId) {
		return CompService.getCompetition(competitionId).getTeam(teamId).getStudent(studentId);
	}

	// Post requests
}
