package vg.inf.util;
public class Student {
	private String name;
	private String id;
	private String major;
	private String rank;
	
	public Student(String name, String id, String major, String rank) {
		this.name = name;
		this.id = id;
		this.major = major;
		this.rank = rank;
	}

	public String getId() {
		return id;
	}

	public String getMajor() {
		return major;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

}
