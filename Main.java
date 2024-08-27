import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean addStudent(Student student) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        return registeredStudents.remove(student);
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + getAvailableSlots();
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.addStudent(this)) {
            registeredCourses.add(course);
            System.out.println("Registration successful!");
        } else {
            System.out.println("Course is full!");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.removeStudent(this);
            registeredCourses.remove(course);
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("Not registered in this course!");
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name;
    }
}

public class Main {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. List Courses");
            System.out.println("2. Register Student");
            System.out.println("3. Register for Course");
            System.out.println("4. Drop Course");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerStudent(scanner);
                    break;
                case 3:
                    registerForCourse(scanner);
                    break;
                case 4:
                    dropCourse(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void listCourses() {
        for (Course course : courses) {
            System.out.println(course);
            System.out.println();
        }
    }

    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(studentID, name));
        System.out.println("Student registered successfully!");
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudentById(studentID);

        if (student != null) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine();
            Course course = findCourseByCode(courseCode);

            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Course not found!");
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudentById(studentID);

        if (student != null) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine();
            Course course = findCourseByCode(courseCode);

            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("Course not found!");
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    private static Student findStudentById(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    static {
        // Sample courses
        courses.add(new Course("CS101", "Intro to Computer Science", "Learn the basics of computer science.", 30, "MWF 10:00-11:00"));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus.", 40, "TTh 12:00-13:30"));
    }
}
