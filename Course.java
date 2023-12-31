public class Course {
    private String courseName;
    private Lecturer lecturer;
    private Student student;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public Student getStudent() {
        return student;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}