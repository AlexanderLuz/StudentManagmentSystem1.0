import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static ArrayList<Student> studentArrayList = new ArrayList<Student>();

    public static void main(String[] args) {
        instantiateStudents();

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
            System.out.println(student.name+" has been added to the Database successfully.");
            System.out.println();
            University.putStudentInUniversity(student);
        }

    }
    public static String askForInput() {
        return scanner.nextLine();
    }



}