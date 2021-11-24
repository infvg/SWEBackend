import java.util.Date;
import java.util.LinkedList;

public class Competition {
	String name;
	String link;
	Date date;
	LinkedList<Team> teams;

	public Competition(String name) {
		this(name, "", null);
	}

	public Competition(String name, String link, Date date) {
		this.name = name;
		this.link = link;
		this.date = date;
		this.teams = new LinkedList<>();
	}
}
