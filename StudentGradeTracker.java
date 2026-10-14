import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=========================================");
        System.out.println("   CodeAlpha - Student Grade Tracker     ");
        System.out.println("=========================================");

        while (true) {
            System.out.print("\nEnter student name (or type 'done' to finish): ");
            String name = scanner.nextLine().trim();

            if (name.equalsIgnoreCase("done")) {
                break;
            }

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Try again.");
                continue;
            }

            double grade = -1;
            while (true) {
                System.out.print("Enter marks/grade for " + name + " (0 - 100): ");
                String input = scanner.nextLine().trim();
                try {
                    grade = Double.parseDouble(input);
                    if (grade >= 0 && grade <= 100) {
                        break;
                    } else {
                        System.out.println("Please enter a valid grade between 0 and 100.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numeric input. Please enter numbers only.");
                }
            }

            students.add(new Student(name, grade));
            System.out.println("Student added successfully!");
        }

        if (students.isEmpty()) {
            System.out.println("\nNo student records entered. Exiting program.");
        } else {
            displaySummaryReport(students);
        }

        scanner.close();
    }

    private static void displaySummaryReport(ArrayList<Student> students) {
        double total = 0;
        double highest = students.get(0).getGrade();
        double lowest = students.get(0).getGrade();
        String highestScorer = students.get(0).getName();
        String lowestScorer = students.get(0).getName();

        System.out.println("\n=========================================");
        System.out.println("             SUMMARY REPORT              ");
        System.out.println("=========================================");
        System.out.printf("%-20s %-10s\n", "Student Name", "Grade");
        System.out.println("-----------------------------------------");

        for (Student s : students) {
            System.out.printf("%-20s %-10.2f\n", s.getName(), s.getGrade());
            total += s.getGrade();

            if (s.getGrade() > highest) {
                highest = s.getGrade();
                highestScorer = s.getName();
            }

            if (s.getGrade() < lowest) {
                lowest = s.getGrade();
                lowestScorer = s.getName();
            }
        }

        double average = total / students.size();

        System.out.println("-----------------------------------------");
        System.out.printf("Total Students : %d\n", students.size());
        System.out.printf("Class Average  : %.2f\n", average);
        System.out.printf("Highest Score  : %.2f (%s)\n", highest, highestScorer);
        System.out.printf("Lowest Score   : %.2f (%s)\n", lowest, lowestScorer);
        System.out.println("=========================================");
    }
}