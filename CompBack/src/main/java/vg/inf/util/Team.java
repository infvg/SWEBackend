package vg.inf.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Team {
	private int id;
	private String name;
	public static int idCounter = 0;
	private List<Student> students;

	public Team(String name) {
		this.id = ++idCounter;
		this.name = name;
		this.students = new ArrayList<>();
	}

	public List<Student> getStudents() {
		return students;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(int id) {
		Iterator<Student> it = students.iterator();
		while (it.hasNext()) {
			Student student = it.next();
			if (student.getId() == id) {
				students.remove(student);
				return;
			}
		}
	}

	public Student getStudent(int id) {
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
