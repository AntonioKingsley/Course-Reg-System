public class Course {
    private String courseId;
    private String title;
    private int creditHours;

    public Course(String courseId, String title, int creditHours) {
        this.courseId = courseId;
        this.title = title;
        this.creditHours = creditHours;
    }

    public void displayCourse() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Title: " + title);
        System.out.println("Credit Hours: " + creditHours);
    }
}