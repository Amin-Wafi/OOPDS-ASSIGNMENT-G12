import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin {
    private List<Student> students;
    private List<Lecturer> lecturers;
    private List<Course> courses;
    private Map<Course, Lecturer> courseAssignLecturer;
    private Map<Course, Student> courseAssignStudent;

    public Admin() {
        this.students = new ArrayList<>();
        this.lecturers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.courseAssignLecturer = new HashMap<>();
        this.courseAssignStudent = new HashMap<>();
    }

    public void createStudent(String name, int id) {
        students.add(new Student(name, id));
        System.out.println("Student name created: " + name);
        System.out.println("Student ID created: " + id);


    }

    public void createLecturer(String name, int id) {
        lecturers.add(new Lecturer(name, id));
        System.out.println("Lecturer name created: " + name);
        System.out.println("Lecturer ID created: " + id);
    }

    public void createCourse(String courseName) {
        courses.add(new Course(courseName));
        System.out.println("Course created: " + courseName);
    }

    public void assignCourseToLecturer(String courseName, String lecturerName, int lecturerId) {
        Course course = findCourse(courseName);
        Lecturer lecturer = findLecturer(lecturerName, lecturerId);

        if (course != null && lecturer != null) {
            courseAssignLecturer.put(course, lecturer);
            course.setLecturer(lecturer);
            System.out.println("Course '" + courseName + "' has now been assigned to Lecturer '" + lecturerName + "'");
        } else {
            System.out.println("Course or Lecturer not found.");
        }
    }

    public void assignCourseToStudent(String courseName, String studentName, int studentId) {
        Course course = findCourse(courseName);
        Student student = findStudent(studentName, studentId);

        if (course != null && student != null) {
            courseAssignStudent.put(course, student);
            course.setStudent(student);
            System.out.println("Course '" + courseName + "' has now been assigned to Student '" + studentName + "'");
        } else {
            System.out.println("Course or Student not found.");
        }
    }

    private Course findCourse(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }

    private Lecturer findLecturer(String lecturerName, int lecturerId) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getName().equals(lecturerName)) {
                return lecturer;
            }
        }
        return null;
    }

    private Student findStudent(String studentName, int studentId) {
        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                return student;
            }
        }
        return null;
    }
} 
    

