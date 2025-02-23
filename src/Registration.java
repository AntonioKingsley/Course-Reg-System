import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Registration {
    String studentId;
    String courseId;

    public Registration(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // ✅ CREATE: Register a student for a course
    public void registerStudent() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO registrations (student_id, course_id) VALUES (?, ?)")) {
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
            System.out.println("✅ Student registered for course!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ READ: Get all students registered in a course
    public static List<Registration> getCourseRegistrations(String courseId) {
        List<Registration> registrations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM registrations WHERE course_id = ?")) {
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                registrations.add(new Registration(rs.getString("student_id"), rs.getString("course_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrations;
    }

    // ✅ DELETE: Unregister a student from a course
    public void unregisterStudent() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM registrations WHERE student_id = ? AND course_id = ?")) {
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
            System.out.println("✅ Student unregistered from course!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
