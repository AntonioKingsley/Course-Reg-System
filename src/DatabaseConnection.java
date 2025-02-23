import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection dBConnection;
    private Connection connection;

    private DatabaseConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "Anto1311//");
    }

    public static DatabaseConnection getInstance() throws ClassNotFoundException, SQLException{
        if(dBConnection == null){
            dBConnection = new DatabaseConnection();
        }
        return dBConnection;
    }

    public static Connection getConnection() {
        try {
            // Using the Singleton instance's connection
            return getInstance().connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}



