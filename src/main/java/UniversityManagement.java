import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UniversityManagement {
    public List<Student> studentArrayList = new ArrayList<>();
    private final AtomicInteger startingID = new AtomicInteger(1000);
    public final University University = new University();

    public void AdminLoginAndActionProcedure() {
        if(AdminLogin()) {
            do {

            }
            while (Main.sng.InputMethods.askForContinue("Are you finished?", 1));
        }
    }

    public boolean AdminLogin() {
        System.out.println("Please enter Master Password: ");
        int answer = Integer.parseInt(Main.sng.InputMethods.askForInput());
        if(answer == University.MasterPassword) {
            System.out.println("Welcome Master!");
            return true;
        }
        else {
            System.out.println("You have no Permission to be here!");
            return false;
        }
    }

    public void chooseAdminAction() {

    }

    // Methods for Creating and Loading Students
    public void instantiateStudents() {
        System.out.println("Enter amount of Students: ");
        int amountOfStudents = Integer.parseInt(Main.sng.InputMethods.askForInput());
        for(int i = 0; i<amountOfStudents; i++) {
            System.out.println("Enter name of Student "+(i+1)+":");
            String name = Main.sng.InputMethods.askForInput();
            System.out.println("Enter balance of "+name+":");
            int balance = Integer.parseInt(Main.sng.InputMethods.askForInput());
            System.out.println("Enter year of "+name+":");
            int year = Integer.parseInt(Main.sng.InputMethods.askForInput());
            System.out.println("Enter bank balance of "+name+":");
            int bankBalance = Integer.parseInt(Main.sng.InputMethods.askForInput());
            studentArrayList.add(new Student(name, balance, year, bankBalance));
            try {
                Main.sng.FileIO.StudentSheetFileWriter(new Student(name, balance, year, bankBalance));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        printAndWriteStudentsToUniversity();
    }

    public void printAndWriteStudentsToUniversity() {
        for(Student student:studentArrayList) {
            putStudentInUniversity(student);
            ConsoleOutputFunctions.printFiller(25,"-");
            student.StVm.printStatus(student);
        }
    }

    public void putStudentInUniversity(Student student) {
        if(!University.StudentList.containsValue(student)) {
            Integer placeholderID = getID(student.year.getYear());
            University.StudentList.put(placeholderID, student);
            University.IDList.put(student.name, placeholderID);
        }
    }
    private Integer getID(int year) {
        int id = startingID.incrementAndGet();
        id = id + (year*10000);
        return id;
    }
    public void putStudentInCourse(Student student, Courses courseEnum) {
        University.CoursesCapacities.put(courseEnum,University.CoursesCapacities.get(courseEnum)+1);
        University.CoursesStudentList.get(courseEnum).add(student);
        if(University.CoursesCapacities.get(courseEnum) == courseEnum.getCapacity()) {
            University.CoursesFilled.put(courseEnum, true);
        }
    }
    public void printStudentInCoursesList(Courses courseEnum) {
        courseEnum.printCourseInfo();
        for(Student student:University.CoursesStudentList.get(courseEnum)) {
            student.StVm.printInfo(student);
        }
    }
}