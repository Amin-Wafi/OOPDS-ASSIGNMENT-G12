import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Admin extends Person {
    private String password;
    private List<Student> students;
    private List<Lecturer> lecturers;
    private List<Course> courses;
    private Map<Course, Lecturer> courseAssignLecturer;
    private Map<Course, Student> courseAssignStudent;
    private SecureRandom random = new SecureRandom();

    public Admin(String name, int id, String password) {
        super(name, id, password);
        this.password = password;
        this.students = new ArrayList<>();
        this.lecturers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.courseAssignLecturer = new HashMap<>();
        this.courseAssignStudent = new HashMap<>();
        initSampleCredentials();
    }

    private void initSampleCredentials() {
        students.add(new Student("student1", 1001, "studentpass"));
        lecturers.add(new Lecturer("lecturer1", 2001, "lecturerpass"));
        // Add more sample credentials as needed

        setId(0001);
        this.password = "adminpass";
    }

    private String generateRandomPassword() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String allChars = uppercaseLetters + numbers;

        StringBuilder passwordBuilder = new StringBuilder();

        // Ensure at least one uppercase letter
        passwordBuilder.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));

        // Generate remaining characters (numbers and lowercase letters)
        for (int i = 1; i < 8; i++) {
            passwordBuilder.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return passwordBuilder.toString();
    }

    public void createStudent(String name, int id) {
        String randomPassword = generateRandomPassword();
        students.add(new Student(name, id, randomPassword));
        System.out.println("Student name created: " + name);
        System.out.println("Student ID created: " + id);
        System.out.println("Student password created: " + randomPassword);
    }

    public void createLecturer(String name, int id) {
        String randomPassword = generateRandomPassword();
        lecturers.add(new Lecturer(name, id, randomPassword));
        System.out.println("Lecturer name created: " + name);
        System.out.println("Lecturer ID created: " + id);
        System.out.println("Lecturer password created: " + randomPassword);
    }

    public void createCourse(String courseCode) {
        courses.add(new Course(courseCode));
        System.out.println("Course created: " + courseCode);
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

    public void assignCourseToStudent(String courseCode, String studentName, int studentId) {
        Course course = findCourse(courseCode);
        Student student = findStudent(studentName, studentId);
    
        if (course != null && student != null) {
            List<Student> studentsInCourse = course.getStudents();
    
            if (studentsInCourse == null) {
                studentsInCourse = new ArrayList<>();
                course.setStudents(studentsInCourse);
            }
            student.registerForCourse(course);

        } else {
            System.out.println("Course or Student not found.");
        }
    }
    

    private Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private Lecturer findLecturer(String lecturerName, int lecturerId) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getName().equals(lecturerName) && lecturer.getId() == lecturerId) {
                return lecturer;
            }
        }
        return null;
    }

    private Student findStudent(String studentName, int studentId) {
        for (Student student : students) {
            if (student.getName().equals(studentName) && student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public void viewStudentsInCourse(String courseCode) {
        Course course = findCourse(courseCode);

        if (course != null) {
            List<Student> students = course.getStudents();

            if (!students.isEmpty()) {
                System.out.println("Students in course '" + courseCode + "':");
                for (Student student : students) {
                    System.out.println(" - " + student.getName() + " (ID: " + student.getId() + ")");
                }
            } else {
                System.out.println("No students in course '" + courseCode + "'.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public Person authenticatePerson(int id, String password) {
        if (this.getId() == id && this.password.equals(password)) {
            return this;
        }

        for (Student student : students) {
            if (student.getId() == id && student.getPassword().equals(password)) {
                return student;
            }
        }

        for (Lecturer lecturer : lecturers) {
            if (lecturer.getId() == id && lecturer.getPassword().equals(password)) {
                return lecturer;
            }
        }

        // If no match is found, return null
        return null;
    }
public void viewStudentsAndLecturersForCourse(String courseCode) {
    System.out.println("Students and Lecturers for Course " + courseCode + ":");

    // Find the course by code
    Course chosenCourse = null;
    for (Course course : courses) {
        if (course.getCourseCode().equals(courseCode)) {
            chosenCourse = course;
            break;
        }
    }

    if (chosenCourse != null) {
        // Display students enrolled in the course
        System.out.println("Students:");
        for (Student student : chosenCourse.getStudents()) {
            System.out.println("    Student: " + student.getName() + " (ID: " + student.getId() + ")");
        }

        // Display lecturers assigned to teach the course
        System.out.println("Lecturers:");
        for (Lecturer lecturer : chosenCourse.getLecturers()) {
            System.out.println("    Lecturer: " + lecturer.getName() + " (ID: " + lecturer.getId() + ")");
        }
    } else {
        System.out.println("Course not found.");
    }
}

public void viewAllStudentsAndLecturers() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter course code: ");
    String courseCode = scanner.nextLine();
    viewStudentsAndLecturersForCourse(courseCode);
}

}
