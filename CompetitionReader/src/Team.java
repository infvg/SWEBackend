import java.util.LinkedList;

public class Team {
	int id;
	String name;
	LinkedList<Student> students;
	int rank;

	public Team(int id, String name) {
		this.id = id;
		this.name = name;
		this.students = new LinkedList<>();
	}
}
