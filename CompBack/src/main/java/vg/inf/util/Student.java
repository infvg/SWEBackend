package vg.inf.util;

public class Student {
	private String name;
	private String id;
	private String major;
	
	public Student(String name, String id, String major) {
		this.name = name;
		this.id = id;
		this.major = major;
	}

	public String getStId() {
		return id;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStId(String stId) {
		this.id = stId;
	}

	public String getMajor() {
		return major;
	}
	public String getName() {
		return name;
	}

}
