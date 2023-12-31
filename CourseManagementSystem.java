import java.util.Scanner;

public class CourseManagementSystem {
    public static void main(String[] args) {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Student and ID");
            System.out.println("2. Create Lecturer and ID");
            System.out.println("3. Create Course");
            System.out.println("4. Assign Course to Lecturer");
            System.out.println("5. Assign Course to Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

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
                    System.out.print("Enter lecturer/student name: ");
                    String assignLecturerName = scanner.nextLine();
                    System.out.print("Enter lecturer/student ID: ");
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
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}
