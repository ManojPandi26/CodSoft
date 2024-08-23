package StudentManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentManagementGUI {
	private StudentDAO dbManager;
	private DefaultTableModel tableModel;
	private JTable studentTable;

	
	private JTextField nameText;
	private JTextField rollText;
	private JTextField gradeText;
	private JTextField emailText;

	public StudentManagementGUI() {
		dbManager = new StudentDAO();
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		JFrame frame = new JFrame("Student Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 450);

		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		frame.add(mainPanel);

		JPanel inputPanel = createInputPanel();
		mainPanel.add(inputPanel, BorderLayout.NORTH);

		JPanel tablePanel = createTablePanel();
		mainPanel.add(tablePanel, BorderLayout.CENTER);

		JPanel buttonPanel = createButtonPanel();
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel instructionsLabel = new JLabel(
				"<html>Instructions:<br>" + "1. Enter student details in the text fields.<br>"
						+ "2. Click 'Add Student' to add a new student.<br>"
						+ "3. Click 'Display Students' to view all students.<br>"
						+ "4. Select a student from the table to update or delete.<br>"
						+ "5. Click 'Update Student' to modify details.<br>"
						+ "6. Click 'Delete Student' to remove the selected student.</html>");
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(instructionsLabel, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Name:"), gbc);

		nameText = new JTextField(20);
		gbc.gridx = 1;
		panel.add(nameText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel("Roll Number:"), gbc);

		rollText = new JTextField(20);
		gbc.gridx = 1;
		panel.add(rollText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(new JLabel("Grade:"), gbc);

		gradeText = new JTextField(20);
		gbc.gridx = 1;
		panel.add(gradeText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(new JLabel("Email:"), gbc);

		emailText = new JTextField(20);
		gbc.gridx = 1;
		panel.add(emailText, gbc);

		return panel;
	}

	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		String[] columnNames = { "Roll Number", "Name", "Grade", "Email" };
		tableModel = new DefaultTableModel(columnNames, 0);
		studentTable = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(studentTable);
		panel.add(scrollPane, BorderLayout.CENTER);

		return panel;
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton addButton = new JButton("Add Student");
		addButton.addActionListener(e -> addStudent());
		panel.add(addButton);

		JButton displayButton = new JButton("Display Students");
		displayButton.addActionListener(e -> displayStudents());
		panel.add(displayButton);

		JButton updateButton = new JButton("Update Student");
		updateButton.addActionListener(e -> updateStudent());
		panel.add(updateButton);

		JButton deleteButton = new JButton("Delete Student");
		deleteButton.addActionListener(e -> deleteStudent());
		panel.add(deleteButton);

		return panel;
	}

	private void addStudent() {
		String name = nameText.getText();
		String rollNumberText = rollText.getText();
		String grade = gradeText.getText();
		String email = emailText.getText();

		if (isInputValid(name, rollNumberText, grade, email)) {
			try {
				int rollNumber = Integer.parseInt(rollNumberText);
				Student student = new Student(rollNumber, name, grade, email);
				dbManager.addStudentToDatabase(student);
				JOptionPane.showMessageDialog(null, "Student added successfully!");
				clearInputFields();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid roll number. Please enter a valid integer.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please fill in all fields.");
		}
	}

	private boolean isInputValid(String name, String rollNumber, String grade, String email) {
		return !(name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty() || email.isEmpty());
	}

	private void clearInputFields() {
		nameText.setText("");
		rollText.setText("");
		gradeText.setText("");
		emailText.setText("");
	}

	private void displayStudents() {
		tableModel.setRowCount(0);
		List<Student> students = dbManager.retrieveStudentsFromDatabase();
		for (Student student : students) {
			Object[] rowData = { student.getRollNumber(), student.getName(), student.getGrade(), student.getEmail() };
			tableModel.addRow(rowData);
		}
	}

	private void updateStudent() {
		int selectedRow = getSelectedRow(studentTable);
		if (selectedRow != -1) {
			int rollNumber = (int) tableModel.getValueAt(selectedRow, 0);
			String name = (String) tableModel.getValueAt(selectedRow, 1);
			String grade = (String) tableModel.getValueAt(selectedRow, 2);
			String email = (String) tableModel.getValueAt(selectedRow, 3);
			dbManager.updateStudentInDatabase(new Student(rollNumber, name, grade, email));
			JOptionPane.showMessageDialog(null, "Student updated successfully!");
		} else {
			JOptionPane.showMessageDialog(null, "Please select a student to update.");
		}
	}

	private void deleteStudent() {
		int selectedRow = getSelectedRow(studentTable);
		if (selectedRow != -1) {
			int rollNumber = (int) tableModel.getValueAt(selectedRow, 0);
			dbManager.deleteStudentFromDatabase(rollNumber);
			tableModel.removeRow(selectedRow);
			JOptionPane.showMessageDialog(null, "Student deleted successfully!");
		} else {
			JOptionPane.showMessageDialog(null, "Please select a student to delete.");
		}
	}

	private int getSelectedRow(JTable studentTable) {
		return studentTable.getSelectedRow();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(StudentManagementGUI::new);
	}
}
