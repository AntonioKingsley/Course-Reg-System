public class Main {
    public static void main(String[] args) {
        System.out.println("Testing student and course");

        Course course = new Course("C001", "combined Maths", 5);
        course.displayCourse();

        Student student = new Student("S0011", "Kamal perera", "maths");
        student.displayStudent();
    }
}