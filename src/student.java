import java.sql.*;
import java.util.*;

class Student {

    Scanner sc = new Scanner(System.in);

    // Show Students
    public void showStudents(Connection con) {
        try {
            String query = "SELECT * FROM student";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("Student details....");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                float marks = rs.getFloat("marks");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Marks: " + marks);
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Add Student
    public void addStudent(Connection con) {
        try {
            System.out.print("Enter name: ");
            String name = sc.next();
            System.out.print("Enter age: ");
            int age = sc.nextInt();
            System.out.print("Enter marks: ");
            float marks = sc.nextFloat();

            String query = "INSERT INTO student(name, age, marks) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setFloat(3, marks);

            ps.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update Student
    public void updateStudent(Connection con) {
        try {
            System.out.print("Enter student ID to update: ");
            int id = sc.nextInt();
            System.out.print("Enter new marks: ");
            float marks = sc.nextFloat();

            String query = "UPDATE student SET marks=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setFloat(1, marks);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete Student
    public void deleteStudent(Connection con) {
        try {
            System.out.print("Enter student ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM student WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}