import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private List<Lecturer> lecturers;
    private List<Student> students;


    public Course(String courseCode) {
        this.courseCode = courseCode;
        this.students = new ArrayList<>();
        this.lecturers = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public List<Lecturer> getLecturers() {
        return lecturers; //return list lect for course
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setLecturer(Lecturer lecturer) { //set lect for course
        this.lecturers.add(lecturer);
    
    }
     public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void setStudents(List<Student> studentsInCourse) {
        this.students = studentsInCourse;
    }
}