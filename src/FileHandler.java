import java.io.*;
import java.util.*;

public class FileHandler {

    static String fileName = "students.txt";

    public static void saveToFile(List<Student> students) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (Student s : students) {
                fw.write(s.id + "," + s.name + "," + s.email + "," + s.course + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public static List<Student> loadFromFile() {
        List<Student> students = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (!file.exists()) return students;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                String course = parts[3];
                students.add(new Student(id, name, email, course));
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error loading file.");
        }
        return students;
    }
}