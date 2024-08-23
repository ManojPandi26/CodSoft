package StudentManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDAO {

	public void addStudentToDatabase(Student student) {
		
        String query = "INSERT INTO students (rollNumber, name, grade, email) VALUES (?, ?, ?, ?)";
        try (Connection con = DBconnection.getconnection(); 
        	PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, student.getRollNumber());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getGrade());
            stmt.setString(4, student.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public Student getStudentFromDatabase(int rollNumber) {
        String query = "SELECT * FROM students WHERE rollNumber = ?";
        try (Connection con = DBconnection.getconnection(); 
        	PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, rollNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(rs.getInt("rollNumber"), rs.getString("name"), rs.getString("grade"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	public List<Student> retrieveStudentsFromDatabase() {
        String query = "SELECT * FROM students";
        try (Connection con = DBconnection.getconnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                int rollNumber = rs.getInt("rollNumber");
                String name = rs.getString("name");
                String grade = rs.getString("grade");
                String email = rs.getString("email");
                students.add(new Student(rollNumber, name, grade, email));
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

	
	public boolean updateStudentInDatabase(Student student) {
	    String updateQuery = "UPDATE students SET name = ?, grade = ?, email = ? WHERE rollNumber = ?";
	    try (Connection con = DBconnection.getconnection();
	         PreparedStatement stmt = con.prepareStatement(updateQuery)) {

			stmt.setString(1, student.getName());         
	        stmt.setString(2, student.getGrade());        
	        stmt.setString(3, student.getEmail());        
	        stmt.setInt(4, student.getRollNumber()); 

	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	
	
	public boolean deleteStudentFromDatabase(int rollNumber) {
	    String deleteQuery = "DELETE FROM students WHERE rollNumber = ?";
	    try (Connection con = DBconnection.getconnection();
	         PreparedStatement stmt = con.prepareStatement(deleteQuery)) {
	        stmt.setInt(1, rollNumber);
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
}
