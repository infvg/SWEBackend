package vg.inf.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Competition {
	private String name;
	private String link;
	private long id;
	private Date date;
	private List<Team> teams;

	public Competition(Long id, String name) {
		this(id, name, "", null);
	}

	public Competition(long id, String name, String link, Date date) {
		this.id = id;
		this.name = name;
		this.link = link;
		this.date = date;
		this.teams = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addTeam(Team team) {
		this.teams.add(team);
	}

	public void removeTeam(long id) {
		Iterator<Team> it = teams.iterator();
		while (it.hasNext()) {
			Team team = it.next();
			if (team.getId() == id) {
				teams.remove(team);
				return;
			}
		}
	}

	public Team getTeam(long id) {
		Iterator<Team> it = teams.iterator();
		while (it.hasNext()) {
			Team team = it.next();
			if (team.getId() == id) {
				return team;
			}
		}
		return null;
	}

	public void removeTeam(String name) {
		Iterator<Team> it = teams.iterator();
		while (it.hasNext()) {
			Team team = it.next();
			if (team.getName().equals(name)) {
				teams.remove(team);
				return;
			}
		}
	}

	public void removeTeam(Team team) {
		teams.remove(team);
	}
}
