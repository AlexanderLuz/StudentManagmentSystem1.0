import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static List<Student> studentArrayList = new ArrayList<>();

    public static void main(String[] args) {
        instantiateStudents();
        performStudentLoginAndActionProcedure();
    }

    // Methods for Creating and Loading Students
    public static void instantiateStudents() {
        System.out.println("Enter amount of Students: ");
        int amountOfStudents = Integer.parseInt(askForInput());
        for(int i = 0; i<amountOfStudents; i++) {
            System.out.println("Enter name of Student "+(i+1)+":");
            String name = askForInput();
            System.out.println("Enter balance of "+name+":");
            int balance = Integer.parseInt(askForInput());
            System.out.println("Enter year of "+name+":");
            int year = Integer.parseInt(askForInput());
            studentArrayList.add(new Student(name, balance, year));
        }
        printAndWriteStudentsToUniversity();
    }

    // Commonly used Methods
    public static String askForInput() {
        return scanner.nextLine();
    }

    // Login and Action procedure
    public static Student studentLogin() {
        System.out.println("Student Login System");
        ConsoleOutputFunctions.printFiller(25, "-");
        System.out.println("Enter your ID: ");
        return University.StudentList.get(Integer.parseInt(askForInput()));
    }
    public static StudentAction chooseAction(Student student) {
        System.out.println("Welcome "+student.name+"!");
        System.out.println("You have 4 Options: \n(1) Show Student Status\n(2) Show Payment Status\n(3) Enroll in new Courses\n(4) Pay for Courses");
        return StudentAction.getEnum(Integer.parseInt(askForInput()));
    }
    public static void performAction(StudentAction action, Student student) {
        ConsoleOutputFunctions.printFiller(25,"-");
        switch(action) {
            case SHOW_STATUS -> printStatus(student);
            case SHOW_PAYMENT_STATUS -> printCoursesWithStatus(student);
            case ENROLL_IN_NEW_COURSE -> {
                printAvailableCourses(student);
                enrollInNewCourse(student);
            }
            default -> payForCourse(student);
        }
    }
    public static void performStudentLoginAndActionProcedure() {
        Student student = studentLogin();
        do {
            StudentAction action = chooseAction(student);
            performAction(action, student);
        }
        while(!askForContinue());
    }

    // Action Methods
    public static void enrollInNewCourse(Student student) {
        System.out.println("What course do you want to enroll in?");
        int answer = Integer.parseInt(askForInput());
        if(!student.coursesPaymentStatus.containsKey(Courses.getEnum(answer)) && student.year.getYear() >= Courses.getEnum(answer).getMinimumYear().getYear()) {
            student.coursesEnrolled.add(Courses.getEnum(answer));
            student.coursesPaymentStatus.put(Courses.getEnum(answer), Status.TUITION_NOT_PAYED);
            System.out.println("Successfully enrolled in Course: "+Courses.getEnum(answer).getName());
        }
        if(student.year.getYear() < Courses.getEnum(answer).getMinimumYear().getYear()) {
            System.out.println("Sorry you cant enroll in this course in your current Year.");
        }
        if(student.coursesPaymentStatus.containsKey(Courses.getEnum(answer))) {
            System.out.println("You are already enrolled in this Course!");
        }
    }
    public static void payForCourse(Student student) {
        printCoursesWithStatus(student);
        System.out.println("What course do you want to pay for?");
        int answer = Integer.parseInt(askForInput());
        if(!student.coursesPaymentStatus.containsKey(Courses.getEnum(answer))) {
            System.out.println("Sorry this course doesn't exist!");
        }
        else {
            if(student.coursesPaymentStatus.get(Courses.getEnum(answer)) == Status.TUITION_NOT_PAYED) {
                if(student.balance < Courses.getEnum(answer).getCost()) {
                    System.out.println("You do not have the funds to pay for this Course!");
                }
                else {
                    student.coursesPaymentStatus.put(Courses.getEnum(answer), Status.TUITION_PAYED);
                    student.balance = student.balance-Courses.getEnum(answer).getCost();
                    System.out.println("Successfully paid for tuition.");
                    System.out.println("New Balance: "+student.balance+"£");
                }
            }
            else {
                System.out.println("You already paid for this Course!");
            }
        }
    }
    public static boolean askForContinue() {
        System.out.println("Did you solve your problem? (1 for yes)");
        int answer = Integer.parseInt(askForInput());
        return answer == 1;
    }

    // Print Methods
    public static void printStatus(Student student) {
        System.out.println(student.name);
        System.out.println("Year: "+student.year.getName()+" | Balance: "+student.balance+"£");
        System.out.println("ID: "+University.IDList.get(student.name));
    }
    public static void printCoursesWithStatus(Student student) {
        if(student.coursesEnrolled.size() == 0) {
            System.out.println("You are not enrolled in any course!");
        }
        else {
            System.out.println("You are enrolled in the courses:");
            for(Courses course:student.coursesEnrolled) {
                course.printCourseInfo();
                System.out.print(" | "+student.coursesPaymentStatus.get(course).getName());
            }
        }
        System.out.println();
    }
    public static void printAvailableCourses(Student student) {
        Courses[] coursesArray = Courses.values();
        for (Courses courses : coursesArray) {
            if (!student.coursesPaymentStatus.containsKey(courses)) {
                courses.printCourseInfo();
            }
        }
        System.out.println();
    }
    private static void printAndWriteStudentsToUniversity() {
        for(Student student:studentArrayList) {
            ConsoleOutputFunctions.printFiller(25,"-");
            University.putStudentInUniversity(student);
            printStatus(student);
        }
    }

    // File IO class
    private static class FileIO {
        public static FileOutputStream fos;
        public static ObjectOutputStream oos;

        public static FileInputStream fis;
        public static ObjectInputStream ois;
    }
}