
package studentmanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();
    private final String fileName = "students.dat";

    public StudentService() {
        loadStudents();
    }

    private void saveStudents() {
        try (ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(fileName))) {

            out.writeObject(students);
            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStudents() {
        try (ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(fileName))) {

            students = (ArrayList<Student>) in.readObject();
            System.out.println("Previous student data loaded!");

        } catch (java.io.FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.id == student.id) {
                System.out.println("Student ID already exists!");
                return;
            }
        }

        students.add(student);
        saveStudents();
        System.out.println("Student added successfully!");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n===== STUDENT DETAILS =====");

        for (Student student : students) {
            student.displayDetails();
            System.out.println("-------------------------");
        }
    }

    public void searchStudent(int id) {
        for (Student student : students) {
            if (student.id == id) {
                System.out.println("Student found!");
                student.displayDetails();
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void deleteStudent(int id) {
        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();

            if (student.id == id) {
                iterator.remove();
                saveStudents();
                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }
}
