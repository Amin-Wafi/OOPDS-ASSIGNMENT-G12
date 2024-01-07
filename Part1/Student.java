import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private List<Course> registeredCourses;
    public Student(String name, int id, String password) {
        super(name, id, password);
        this.registeredCourses = new ArrayList<>();
    }

    public void registerForCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.addStudent(this);
            System.out.println(getName() + " has registered for the course: " + course.getCourseCode());
        } else {
            System.out.println(getName() + " is already registered for the course: " + course.getCourseCode());
        }
    }    

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }    

    public void viewAllRegisteredCourses() {
        if (!registeredCourses.isEmpty()) {
            System.out.println("Courses registered by " + getName() + " (ID: " + getId() + "):");
            for (Course course : registeredCourses) {
                System.out.println(" - " + course.getCourseCode());
            }
        } else {
            System.out.println(getName() + " is not registered for any courses.");
        }
    }
}