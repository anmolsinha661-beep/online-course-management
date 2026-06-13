import java.util.Scanner;

// Course Class
class Course {
    private String courseName;
    private String courseId;
    private String duration;

    private Student[] enrolledStudents = new Student[50];
    private int studentCount = 0;

    // Constructor
    Course(String courseName, String courseId, String duration) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.duration = duration;
    }

    // Add student in course
    public void addEnrolledStudent(Student s) {
        enrolledStudents[studentCount] = s;
        studentCount++;
    }

    // Get course name
    public String getCourseName() {
        return courseName;
    }

    // Display course details
    public void getCourseDetails() {
        System.out.println("\nCourse Name : " + courseName);
        System.out.println("Course ID   : " + courseId);
        System.out.println("Duration    : " + duration);

        System.out.println("Enrolled Students:");
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i + 1) + ". "
                    + enrolledStudents[i].getStudentName()
                    + " - Progress: "
                    + enrolledStudents[i].getProgress() + "%");
        }
    }
}

// Student Class
class Student {
    private String studentName;
    private String studentId;
    private int progress;

    private Course[] enrolledCourses = new Course[10];
    private int courseCount = 0;

    // Constructor
    Student(String studentName, String studentId) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.progress = 0;
    }

    // Enroll in course
    public void enroll(Course c) {
        enrolledCourses[courseCount] = c;
        courseCount++;

        c.addEnrolledStudent(this);

        System.out.println(studentName + " enrolled in "
                + c.getCourseName());
    }

    // Update progress
    public void updateProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            this.progress = progress;
        } else {
            System.out.println("Invalid Progress!");
        }
    }

    // Get progress
    public int getProgress() {
        return progress;
    }

    // Get student name
    public String getStudentName() {
        return studentName;
    }

    // Display student info
    public void displayStudentInfo() {
        System.out.println("\nStudent Name : " + studentName);
        System.out.println("Student ID   : " + studentId);
        System.out.println("Progress     : " + progress + "%");

        System.out.println("Enrolled Courses:");
        for (int i = 0; i < courseCount; i++) {
            System.out.println((i + 1) + ". "
                    + enrolledCourses[i].getCourseName());
        }
    }
}

// Main Class
public class CourseSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Course[] courses = new Course[10];
        Student[] students = new Student[10];

        int courseCount = 0;
        int studentCount = 0;

        int choice;

        do {
            System.out.println("\n===== ONLINE COURSE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Enroll Student");
            System.out.println("4. Update Progress");
            System.out.println("5. View Course Details");
            System.out.println("6. View Student Details");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    // Add Course
                    System.out.print("Enter Course Name: ");
                    String cname = sc.nextLine();

                    System.out.print("Enter Course ID: ");
                    String cid = sc.nextLine();

                    System.out.print("Enter Duration: ");
                    String duration = sc.nextLine();

                    courses[courseCount] =
                            new Course(cname, cid, duration);

                    courseCount++;

                    System.out.println("Course Added Successfully!");
                    break;

                case 2:
                    // Add Student
                    System.out.print("Enter Student Name: ");
                    String sname = sc.nextLine();

                    System.out.print("Enter Student ID: ");
                    String sid = sc.nextLine();

                    students[studentCount] =
                            new Student(sname, sid);

                    studentCount++;

                    System.out.println("Student Added Successfully!");
                    break;

                case 3:
                    // Enroll Student
                    if (studentCount == 0 || courseCount == 0) {
                        System.out.println("Add students and courses first!");
                        break;
                    }

                    System.out.println("Select Student:");
                    for (int i = 0; i < studentCount; i++) {
                        System.out.println(i + ". "
                                + students[i].getStudentName());
                    }

                    int sIndex = sc.nextInt();

                    System.out.println("Select Course:");
                    for (int i = 0; i < courseCount; i++) {
                        System.out.println(i + ". "
                                + courses[i].getCourseName());
                    }

                    int cIndex = sc.nextInt();

                    students[sIndex].enroll(courses[cIndex]);
                    break;

                case 4:
                    // Update Progress
                    System.out.println("Select Student:");
                    for (int i = 0; i < studentCount; i++) {
                        System.out.println(i + ". "
                                + students[i].getStudentName());
                    }

                    int pIndex = sc.nextInt();

                    System.out.print("Enter Progress (%): ");
                    int prog = sc.nextInt();

                    students[pIndex].updateProgress(prog);

                    System.out.println("Progress Updated!");
                    break;

                case 5:
                    // View Course Details
                    System.out.println("Select Course:");
                    for (int i = 0; i < courseCount; i++) {
                        System.out.println(i + ". "
                                + courses[i].getCourseName());
                    }

                    int viewCourse = sc.nextInt();

                    courses[viewCourse].getCourseDetails();
                    break;

                case 6:
                    // View Student Details
                    System.out.println("Select Student:");
                    for (int i = 0; i < studentCount; i++) {
                        System.out.println(i + ". "
                                + students[i].getStudentName());
                    }

                    int viewStudent = sc.nextInt();

                    students[viewStudent].displayStudentInfo();
                    break;

                case 7:
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}