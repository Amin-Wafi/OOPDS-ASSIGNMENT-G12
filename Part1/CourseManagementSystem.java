import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CourseManagementSystem {
    public static void main(String[] args) {
        Admin admin = new Admin("admin", 0001, "adminpass");
        Scanner scanner = new Scanner(System.in);
        
while (true) {
            Person loggedInPerson = login(admin, scanner);

            if (loggedInPerson != null) {
                if (loggedInPerson instanceof Admin) {
                    System.out.println("Login successful. Welcome, " + loggedInPerson.getClass().getSimpleName() + "!");
                    showAdminMainMenu(admin, scanner);
                } else if (loggedInPerson instanceof Student) {
                    System.out.println("Login successful. Welcome, " + loggedInPerson.getName() + "!");
                    showStudentMenu((Student) loggedInPerson, admin, scanner);
                } else if (loggedInPerson instanceof Lecturer) {
                    System.out.println("Login successful. Welcome, " + loggedInPerson.getName() + "!");
                    showLecturerMenu((Lecturer) loggedInPerson, admin, scanner);
                }
            } else {
                System.out.println("Login failed. Invalid credentials. Please try again.");
            }
        }
    }

    private static void showAdminMainMenu(Admin admin, Scanner scanner) {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("1. Create Student and ID");
            System.out.println("2. Create Lecturer and ID");
            System.out.println("3. Create Course");
            System.out.println("4. Assign Course to Lecturer");
            System.out.println("5. Assign Course to Student");
            System.out.println("6. View all the students and the lecturers for courses");
            System.out.println("7. Logout");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for choice. Please enter a valid number.");
                scanner.nextLine(); // consume the invalid input
                continue; // restart the loop
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();

                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    admin.createStudent(studentName, studentId);
                    break;
                case 2:
                    System.out.print("Enter lecturer name: ");
                    String lecturerName = scanner.nextLine();

                    System.out.print("Enter lecturer ID: ");
                    int lecturerId = scanner.nextInt();
                    admin.createLecturer(lecturerName, lecturerId);
                    break;
                case 3:
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    admin.createCourse(courseName);
                    break;
                case 4:
                    System.out.print("Enter course name: ");
                    String assignCourseNameLecturer = scanner.nextLine();
                    System.out.print("Enter lecturer name: ");
                    String assignLecturerName = scanner.nextLine();
                    System.out.print("Enter lecturer ID: ");
                    int assignLecturerId = scanner.nextInt();
                    admin.assignCourseToLecturer(assignCourseNameLecturer, assignLecturerName, assignLecturerId);
                    break;
                case 5:
                    System.out.print("Enter course name: ");
                    String assignCourseNameStudent = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String assignStudentName = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    int assignStudentId = scanner.nextInt();
                    admin.assignCourseToStudent(assignCourseNameStudent, assignStudentName, assignStudentId);
                    break;
                case 6:
                
                admin.viewAllStudentsAndLecturers();
                break;
                case 7:
                    System.out.println("Logout successful.");
                    isLoggedIn = false;
                    break;
                case 8:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void showStudentMenu(Student student, Admin admin, Scanner scanner) {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("1. Register for a Course");
            System.out.println("2. Logout");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for choice. Please enter a valid number.");
                scanner.nextLine(); // consume the invalid input
                continue; // restart the loop
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    admin.assignCourseToStudent(courseName, student.getName(), student.getId());
                    break;
                case 2:
                    System.out.println("Logout successful.");
                    isLoggedIn = false;
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void viewRegisteredCourses(Student student) {
    List<Course> registeredCourses = student.getRegisteredCourses();

    if (!registeredCourses.isEmpty()) {
        System.out.println("Registered Courses:");
        for (Course course : registeredCourses) {
            System.out.println(" - " + course.getCourseCode());
        }
    } else {
        System.out.println("You are not registered for any courses.");
        }
    }

    private static void showLecturerMenu(Lecturer lecturer, Admin admin, Scanner scanner) {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("1. View Students in My Course");
            System.out.println("2. Logout");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for choice. Please enter a valid number.");
                scanner.nextLine(); // consume the invalid input
                continue; // restart the loop
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    admin.viewStudentsInCourse(courseName);
                    break;
                case 2:
                    System.out.println("Logout successful.");
                    isLoggedIn = false;
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static Person login(Admin admin, Scanner scanner) {
        System.out.println("Please log in");

        System.out.print("Enter your ID: ");
        int id = 0;

        try {
            id = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for ID. Please enter a valid number.");
            scanner.nextLine(); // consume the invalid input
            return null;
        }

        scanner.nextLine(); // consume the newline

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        return admin.authenticatePerson(id, password);
    }
}