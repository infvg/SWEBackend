package vg.inf.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Team {
	private long id;
	private String name;
	public static long idCounterTeam = 0;
	private List<Student> students;

	public Team(long id, String name) {
		this.id = id;
		this.name = name;
		this.students = new ArrayList<>();
	}

	public List<Student> getStudents() {
		return students;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(long id) {
		Iterator<Student> it = students.iterator();
		while (it.hasNext()) {
			Student student = it.next();
			if (student.getId() == id) {
				students.remove(student);
				return;
			}
		}
	}

	public Student getStudent(long id) {
		Iterator<Student> it = students.iterator();
		while (it.hasNext()) {
			Student student = it.next();
			if (student.getId() == id) {
				return student;
			}
		}
		return null;
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
