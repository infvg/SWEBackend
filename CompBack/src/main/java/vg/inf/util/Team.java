package vg.inf.util;
import java.util.Iterator;
import java.util.LinkedList;

public class Team {
	private int id;
	private String name;
	private LinkedList<Student> students;

	public Team(int id, String name) {
		this.id = id;
		this.name = name;
		this.students = new LinkedList<>();
	}

	public LinkedList<Student> getStudents() {
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
