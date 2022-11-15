import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    public int balance;
    public String name;
    public ArrayList<Courses> coursesEnrolled = new ArrayList<Courses>();
    public HashMap<Courses, Status> coursesPaymentStatus = new HashMap<Courses, Status>();
    public Year year;

    Student(String name, int balance, int year) {
        this.balance = balance;
        this.name = name;
        this.year = Year.getEnum(year);
    }
}
