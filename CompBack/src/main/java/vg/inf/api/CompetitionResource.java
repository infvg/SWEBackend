package vg.inf.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vg.inf.util.Competition;
import vg.inf.util.Student;
import vg.inf.util.Team;

@RestController
@CrossOrigin(origins = "*")
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
	
	@GetMapping("/competitions/{competitionId}/teams")
	public List<Team> getTeams(@PathVariable String competitionId) {
		return compService.getCompetition(competitionId).getTeams();
	}
	// Post requests

	@PostMapping("/competitions")
	public ResponseEntity<Void> createCompetition(@RequestBody Competition comp) {
		compService.admodCompetition(comp);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{competitionId}")
				.buildAndExpand(comp.getId()).toUri()).build();

	}
	// Put requests

	@PutMapping("/competitions")
	public ResponseEntity<Void> modifyCompetition(@RequestBody Competition comp) {
		compService.admodCompetition(comp);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{competitionId}")
				.buildAndExpand(comp.getId()).toUri()).build();

	}
	
	// Delete requests


	@PostMapping("/competitions/delete")
	public ResponseEntity<Void> deleteCompetition(@RequestBody Competition comp) {
		compService.removeCompetition(comp);
		return ResponseEntity.noContent().build();
	}
}
