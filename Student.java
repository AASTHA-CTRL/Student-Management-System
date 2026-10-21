
package studentmanagement;

public class Student implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    int id;
    String name;
    String course;
    double marks;

    public Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public void displayDetails() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
    }
}
