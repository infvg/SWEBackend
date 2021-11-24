import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompetitionResource {

	@Autowired
	private CompetitionsService CompService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Competition> getAllCompetitions() {
		return CompService.getAll();
	}
}
