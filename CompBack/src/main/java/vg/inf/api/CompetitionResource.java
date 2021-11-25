package vg.inf.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vg.inf.util.Competition;
import vg.inf.util.Student;
import vg.inf.util.Team;

@RestController
public class CompetitionResource {

	@Autowired
	private CompetitionsService compService;

	// Get Requests

	@GetMapping("/competitions")
	public List<Competition> getAllCompetitions() {
		return compService.getAll();
	}

	@GetMapping("/competitions/{competitionId}")
	public Competition getCompetition(@PathVariable String competitionId) {
		return compService.getCompetition(competitionId);
	}

	@GetMapping("/competitions/{competitionId}/teams/{teamId}")
	public Team getTeam(@PathVariable String competitionId, @PathVariable int teamId) {
		return compService.getCompetition(competitionId).getTeam(teamId);
	}

	@GetMapping("/competitions/{competitionId}/teams")
	public List<Team> getTeams(@PathVariable String competitionId) {
		return compService.getCompetition(competitionId).getTeams();
	}

	@GetMapping("/competitions/{competitionId}/teams/{teamId}/students")
	public List<Student> getStudentsInTeam(@PathVariable String competitionId, @PathVariable int teamId) {
		return compService.getCompetition(competitionId).getTeam(teamId).getStudents();
	}

	@GetMapping("/competitions/{competitionId}/teams/{teamId}/students/{studentId}")
	public Student getStudent(@PathVariable String competitionId, @PathVariable int teamId,
			@PathVariable int studentId) {
		return compService.getCompetition(competitionId).getTeam(teamId).getStudent(studentId);
	}

	// Post requests

	@PostMapping("/competitions")
	public ResponseEntity<Void> competitionCommand(@RequestHeader("command") String command,
			@RequestBody Competition comp) {
		if (command.equals("admod")) {
			compService.admodCompetition(comp);
			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{competitionId}")
					.buildAndExpand(comp.getId()).toUri()).build();
		} else if (command.equals("delete")) {
			compService.removeCompetition(comp);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/competitions/{competitionId}/team")
	public ResponseEntity<Void> teamCommand(@RequestHeader("command") String command, @RequestBody String compId,
			@RequestBody Team team) {
		if (command.equals("admod")) {
			compService.admodTeamToCompetition(compId, team);
			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{teamId}")
					.buildAndExpand(team.getId()).toUri()).build();
		} else if (command.equals("delete")) {
			compService.removeTeamFromComp(compId, team);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.noContent().build();
	}
}
