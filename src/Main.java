import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static ArrayList<Student> studentArrayList = new ArrayList<Student>();

    public static void main(String[] args) {
        instantiateStudents();
        chooseAction(studentLogin());

    }

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
            student.printStatus();
        }
    }
    public static String askForInput() {
        return scanner.nextLine();
    }
    public static Student studentLogin() {
        System.out.println("Student Login System");
        ConsoleOutputFunctions.printFiller(25, "-");
        System.out.println("Enter your ID: ");
        return University.StudentList.get(Integer.parseInt(askForInput()));
    }
    public static int chooseAction(Student student) {
        System.out.println("Welcome "+student.name+"!");
        System.out.println("You have 4 Options: \n(1) Show Student Status\n(2) Show Payment Status\n(3) Enroll in new Courses\n(4) Pay for Courses");
        return Integer.parseInt(askForInput());
    }
}