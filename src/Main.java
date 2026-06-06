import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Student> students = FileHandler.loadFromFile();
    static int nextId = getNextId();

    static int getNextId() {
        int max = 0;
        for (Student s : students) {
            if (s.id > max) max = s.id;
        }
        return max + 1;
    }

    public static void main(String[] args) {
        System.out.println("=== Student Management System ===");

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addStudent();    break;
                case 2: viewStudents();  break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter course: ");
        String course = sc.nextLine();

        Student s = new Student(nextId++, name, email, course);
        students.add(s);
        FileHandler.saveToFile(students);
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {
        System.out.print("Enter name to search: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Student s : students) {
            if (s.name.toLowerCase().contains(keyword)) {
                s.display();
                found = true;
            }
        }
        if (!found) System.out.println("No student found.");
    }

    static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.id == id) {
                System.out.print("New name (current: " + s.name + "): ");
                s.name = sc.nextLine();
                System.out.print("New email (current: " + s.email + "): ");
                s.email = sc.nextLine();
                System.out.print("New course (current: " + s.course + "): ");
                s.course = sc.nextLine();

                FileHandler.saveToFile(students);
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.id == id) {
                it.remove();
                FileHandler.saveToFile(students);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }
}