package vg.inf.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Team implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8631126125121754175L;
	private String name;
	private boolean isWinner;
	private List<Student> students;

	public Team(String name) {
		this.name = name;
		isWinner = false;
		this.students = new ArrayList<>();
	}

	public Team() {
	}
	public Team(String name, boolean isWinner, List<Student> students) {
		this.name = name;
		this.isWinner = isWinner;
		this.students = students;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setWinner() {
		this.isWinner = true;
	}

	public boolean isWinner() {
		return isWinner;
	}
	public String getName() {
		return name;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(String name) {
		Iterator<Student> it = students.iterator();
		while (it.hasNext()) {
			Student student = it.next();
			if (student.getName().equals(name)) {
				students.remove(student);
				return;
			}
		}
	}

	public void removeStudent(Student student) {
		students.remove(student);
	}
}
