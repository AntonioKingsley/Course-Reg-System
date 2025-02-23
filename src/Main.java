public class Main {
    public static void main(String[] args) {
        System.out.println("Testing DB...");

        if (DatabaseConnection.getConnection() != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}