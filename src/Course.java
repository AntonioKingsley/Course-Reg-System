import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseId;
    private String title;
    private int creditHours;

    public Course(String courseId, String title, int creditHours) {
        this.courseId = courseId;
        this.title = title;
        this.creditHours = creditHours;
    }

    // ✅ CREATE: Add a new course
    public void saveToDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO courses (course_id, title, credit_hours) VALUES (?, ?, ?)")) {
            stmt.setString(1, courseId);
            stmt.setString(2, title);
            stmt.setInt(3, creditHours);
            stmt.executeUpdate();
            System.out.println("✅ Course added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ READ: Fetch all courses
    public static List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM courses");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(rs.getString("course_id"), rs.getString("title"), rs.getInt("credit_hours")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // ✅ UPDATE: Modify course details
    public void updateCourse(String newTitle, int newCreditHours) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE courses SET title = ?, credit_hours = ? WHERE course_id = ?")) {
            stmt.setString(1, newTitle);
            stmt.setInt(2, newCreditHours);
            stmt.setString(3, courseId);
            stmt.executeUpdate();
            System.out.println("✅ Course updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ DELETE: Remove a course
    public void deleteCourse() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM courses WHERE course_id = ?")) {
            stmt.setString(1, courseId);
            stmt.executeUpdate();
            System.out.println("✅ Course deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayCourse() {
        System.out.println("ID: " + courseId + " | Title: " + title + " | Credit Hours: " + creditHours);
    }
}
