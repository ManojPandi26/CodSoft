package StudentGradeCalc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentGradeCaculator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numSubjects;
		do {
			System.out.print("Enter the number of subjects (1-10): ");
			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number.");
				sc.next();
			}
			numSubjects = sc.nextInt();
		} while (numSubjects < 1 || numSubjects > 10);

		List<Integer> subjectMarks = new ArrayList<>(numSubjects);
		int totalMarks = 0;

		for (int i = 0; i < numSubjects; i++) {
			int marks;
			do {
				System.out.print("Enter mark for subject " + (i + 1) + " (0-100): ");
				while (!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter a number.");
					sc.next();
				}
				marks = sc.nextInt();
				if (marks < 0 || marks > 100) {
					System.out.println("Invalid mark. Please enter a mark between 0 & 100.");
				}
			} while (marks < 0 || marks > 100);
			subjectMarks.add(marks);
			totalMarks += marks;
		}

		double averagePercentage = (double) totalMarks / numSubjects;

		char grade = determineGrade(averagePercentage);

		System.out.println("\nResults:");
		System.out.println("------------------------------------");
		System.out.printf("%-15s %-10s\n", "Subject", "Marks");
		System.out.println("------------------------------------");
		for (int i = 0; i < numSubjects; i++) {
			System.out.printf("%-15s %-10d\n", "Subject " + (i + 1), subjectMarks.get(i));
		}
		System.out.println("------------------------------------");
		System.out.printf("%-15s %-10d\n", "Total Marks", totalMarks);
		System.out.printf("%-15s %-10.2f\n", "Average", averagePercentage);
		System.out.printf("%-15s %-10c\n", "Grade", grade);
		System.out.println("------------------------------------");

		sc.close();
	}

	private static char determineGrade(double averagePercentage) {
		if (averagePercentage >= 90) {
			return 'A';
		} else if (averagePercentage >= 80) {
			return 'B';
		} else if (averagePercentage >= 70) {
			return 'C';
		} else if (averagePercentage >= 60) {
			return 'D';
		} else {
			return 'F';
		}
	}
}
