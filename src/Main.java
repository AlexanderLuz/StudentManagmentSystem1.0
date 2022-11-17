import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static List<Student> studentArrayList = new ArrayList<>();

    public static void main(String[] args) {
        new StudentSheetFileIO();
        instantiateStudents();
        do {
            performStudentLoginAndActionProcedure();
        }
        while(!askForContinue("Is there another Student that wants to login?", 1));
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
            System.out.println("Enter bank balance of "+name+":");
            int bankBalance = Integer.parseInt(askForInput());
            studentArrayList.add(new Student(name, balance, year, bankBalance));
        }
        printAndWriteStudentsToUniversity();
    }

    // Commonly used Methods
    public static String askForInput() {
        return scanner.nextLine();
    }
    public static boolean askForContinue(String question, int i) {
        System.out.println(question+" ("+i+" for yes)");
        int answer = Integer.parseInt(askForInput());
        return answer == i;
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
        int answer = Integer.parseInt(askForInput());
        return StudentAction.getEnum(answer);
    }
    public static void performAction(StudentAction action, Student student) {
        ConsoleOutputFunctions.printFiller(25,"-");
        switch(action) {
            case FINISHED -> {
                System.out.println("Have a nice day "+student.name+"!");

            }
            case SHOW_STATUS -> student.printStatus();
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
        ConsoleOutputFunctions.printFiller(25, "-");
        do {
            StudentAction action = chooseAction(student);
            performAction(action, student);
            if (action == StudentAction.FINISHED) {
                return;
            }
        }
        while(!askForContinue("Did you solve your problem?", 1));
    }

    // Action Methods
    public static void enrollInNewCourse(Student student) {
        System.out.println("What course do you want to enroll in?");
        int answer = Integer.parseInt(askForInput());
        Courses courseEnum = Courses.getEnum(answer);
        if(!student.coursesPaymentStatus.containsKey(courseEnum) && student.year.getYear() >= courseEnum.getMinimumYear().getYear() && !University.CoursesFilled.get(courseEnum)) {
            student.coursesEnrolled.add(courseEnum);
            student.coursesPaymentStatus.put(courseEnum, Status.TUITION_NOT_PAYED);
            University.putStudentInCourse(student,courseEnum);
            System.out.println("Successfully enrolled in Course: "+ courseEnum.getName());
        }
        else {
            if (student.year.getYear() < courseEnum.getMinimumYear().getYear()) {
                System.out.println("Sorry you cant enroll in this course in your current Year.");
            }
            if (student.coursesPaymentStatus.containsKey(courseEnum)) {
                System.out.println("You are already enrolled in this Course!");
            }
            if(University.CoursesFilled.get(courseEnum)) {
                System.out.println("Sorry this course is filled!");
            }
        }
    }
    public static void payForCourse(Student student) {
        if(student.coursesEnrolled.size() == 0) {
            return;
        }
        printCoursesWithStatus(student);
        System.out.println("What course do you want to pay for?");
        int answer = Integer.parseInt(askForInput());
        Courses courseEnum = Courses.getEnum(answer);
        if(!student.coursesPaymentStatus.containsKey(courseEnum)) {
            System.out.println("Sorry this course doesn't exist!");
        }
        else {
            if(student.coursesPaymentStatus.get(courseEnum) == Status.TUITION_NOT_PAYED) {
                if(student.balance < courseEnum.getCost()) {
                    System.out.println("You do not have the funds to pay for this Course!");
                }
                else {
                    student.coursesPaymentStatus.put(courseEnum, Status.TUITION_PAYED);
                    student.balance = student.balance-courseEnum.getCost();
                    System.out.println("Successfully paid for tuition.");
                    System.out.println("New Balance: "+student.balance+"£");
                }
            }
            else {
                System.out.println("You already paid for this Course!");
            }
        }
    }


    // Print Methods
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
            University.putStudentInUniversity(student);
            ConsoleOutputFunctions.printFiller(25,"-");
            student.printStatus();
        }
    }

    // File IO class
    private static class StudentSheetFileIO {
        private static final String Path = "StudentSheet.csv";

        BufferedReader reader;
        String line = "";

        {
            try {
                reader = new BufferedReader(new FileReader(Path));

                while((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    studentArrayList.add(new Student(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3])));
                }
                printAndWriteStudentsToUniversity();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}