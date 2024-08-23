package StudentManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {

	 private List<Student> students;

	    public StudentManagementSystem() {
	        students = new ArrayList<>();
	    }

	    public void addStudent(Student student) {
	        students.add(student);
	    }

	    public void removeStudent(int rollNumber) {
	        students.removeIf(student -> student.getRollNumber() == rollNumber);
	    }

	    public Student searchStudent(int rollNumber) {
	        for (Student student : students) {
	            if (student.getRollNumber() == rollNumber) {
	                return student;
	            }
	        }
	        return null;
	    }

	    public List<Student> getAllStudents() {
	        return students;
	    }

}
