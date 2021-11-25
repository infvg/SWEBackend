package vg.inf.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vg.inf.CompetitionsService;
import vg.inf.util.Competition;

@RestController
public class CompetitionResource {

	@Autowired
	private CompetitionsService CompService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Competition> getAllCompetitions() {
		return CompService.getAll();
	}
}
