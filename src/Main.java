public class Main {
    public static void main(String[] args) {
        System.out.println("Course Registration System Starting...");

        Student student = new Student("S002", "Bob Smith", "Information Technology");
        student.saveToDatabase();

        Course course = new Course("CS101", "Programming Basics", 3);
        course.saveToDatabase();


        Registration registration = new Registration("S002", "CS101");
        registration.registerStudent();


        System.out.println("\nStudents registered in CS101:");
        for (Registration r : Registration.getCourseRegistrations("CS101")) {
            System.out.println("Student ID: " + r.studentId + " | Course ID: " + r.courseId);
        }


        registration.unregisterStudent();


        DatabaseConnection.closeConnection();
    }
}
