package vg.inf.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public class Competition implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4350446688823794071L;
	
	private String name;
	private String link;
	private String id;
	@DateTimeFormat  Date date;
	private List<Team> teams;
	
	
	public Competition() {
		super();
	}
	
	public Competition(String id, String name) {
		this(id, name, "", null);
	}

	public Competition(String id, String name, String link, Date date) {
		this.id = id;
		this.name = name;
		this.link = link;
		this.date = date;
		this.teams = new ArrayList<>();
	}
	public Competition(String name, String link, Date date) {
		this(UUID.randomUUID().toString(), name, link, date);
	}
	public Competition(String id, String name, String link, Date date, List<Team> teams) {
		this.id = id;
		this.name = name;
		this.link = link;
		this.date = date;
		this.teams = teams;
	}

	public String getId() {
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
	@Override
	public String toString() {
		String s = String.format("name: %s, date: %s, link: %s, id: %s\n", name,date,link,id);
		return s;
	}
	public void removeTeam(Team team) {
		teams.remove(team);
	}
}
