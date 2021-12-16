package vg.inf.util;

import java.io.Serializable;

public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2656367673411073448L;
	private String name;
	private String id;
	private String major;
	
	public Student() {
	}
	
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
