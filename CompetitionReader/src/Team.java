import java.util.LinkedList;

public class Team {
	int id;
	LinkedList<Student> students;
	int rank;
	public Team(int id) {
		this.id = id;
		this.students = new LinkedList<>();
	}
}
