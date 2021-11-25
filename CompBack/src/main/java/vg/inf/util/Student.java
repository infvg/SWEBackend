package vg.inf.util;

public class Student {
	private String name;
	private String stId;
	private String major;
	private String rank;
	public static int idCounter = 0;
	private int id;

	public Student(String name, String stId, String major, String rank) {
		this.name = name;
		this.id = ++idCounter;
		this.stId = stId;
		this.major = major;
		this.rank = rank;
	}

	public int getId() {
		return id;
	}

	public String getStId() {
		return stId;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStId(String stId) {
		this.stId = stId;
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
