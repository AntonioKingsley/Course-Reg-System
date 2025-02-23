import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/CourseRegDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Anto1311//";

    private static Connection connection;

    // Create a single database connection (Singleton Pattern)
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Close the connection when the application exits
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Reset connection after closing
                System.out.println("✅ Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
