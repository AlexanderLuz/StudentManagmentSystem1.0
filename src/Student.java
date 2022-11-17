import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Student implements Serializable {
    public final Student_Viewmodel StVm = new Student_Viewmodel();
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
}
