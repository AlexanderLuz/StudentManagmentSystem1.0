import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static ArrayList<Student> studentArrayList = new ArrayList<Student>();

    public static void main(String[] args) {
        instantiateStudents();
        performStudentLoginAndActionProcedure();


    }

    // Methods for Creating new Students
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
        for(Student student:studentArrayList) {
            ConsoleOutputFunctions.printFiller(25,"-");
            University.putStudentInUniversity(student);
            printStatus(student);
        }
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
                Courses.printAvailableCourses(student);
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
        if(!student.coursesPaymentStatus.containsKey(Courses.getEnum(answer))) {
            student.coursesEnrolled.add(Courses.getEnum(answer));
            student.coursesPaymentStatus.put(Courses.getEnum(answer), Status.TUITION_NOT_PAYED);
            System.out.println("Successfully enrolled in Course: "+Courses.getEnum(answer).getName());
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
                System.out.println();
                System.out.print(course.getIndex()+". ");
                System.out.printf("%-20s", course.getName());
                System.out.printf("%10s", student.coursesPaymentStatus.get(course).getName());
                System.out.print(" | Cost: "+course.getCost()+"£");
            }
        }
        System.out.println();
    }

}