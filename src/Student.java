import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Student implements Serializable {
    public int balance;
    public int bankBalance;
    public String name;
    public ArrayList<Courses> coursesEnrolled = new ArrayList<Courses>();
    public HashMap<Courses, Status> coursesPaymentStatus = new HashMap<Courses, Status>();
    public Year year;

    Student(String name, int balance, int year, int bankBalance) {
        this.balance = balance;
        this.name = name;
        this.year = Year.getEnum(year);
        this.bankBalance = bankBalance;
    }

    public void printStatus() {
        System.out.println(name);
        System.out.println("Year: "+year.getName()+" | Balance: "+balance+"£"+" | Bank Balance: "+bankBalance);
        System.out.println("ID: "+University.IDList.get(name));
        for(Courses course:coursesEnrolled) {
            course.printCourseInfo();
        }
    }
    public void printInfo() {
        System.out.println(name);
        ConsoleOutputFunctions.printBreak();
        System.out.print(year.getName());
    }
}
