import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private String program;

    public Student(String studentId, String name, String program) {
        this.studentId = studentId;
        this.name = name;
        this.program = program;
    }

    // Add
    public void saveToDatabase() {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed!");
            return;
        }

        String query = "INSERT INTO students (student_id, name, program) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentId);
            stmt.setString(2, name);
            stmt.setString(3, program);
            stmt.executeUpdate();
            System.out.println("✅ Student registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed!");
            return students;
        }

        String query = "SELECT * FROM students";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(rs.getString("student_id"), rs.getString("name"), rs.getString("program")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // UPDATE
    public void updateStudent(String newName, String newProgram) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed!");
            return;
        }

        String query = "UPDATE students SET name = ?, program = ? WHERE student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setString(2, newProgram);
            stmt.setString(3, studentId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                this.name = newName;
                this.program = newProgram;
                System.out.println("✅ Student updated successfully!");
            } else {
                System.out.println("❌ Student not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteStudent() {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed!");
            return;
        }

        String query = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Student deleted successfully!");
            } else {
                System.out.println("❌ Student not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display student details
    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Program: " + program);
    }
}
