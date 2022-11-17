import java.io.Serializable;

public class Student_Viewmodel implements Serializable {
    public void printStatus(Student student) {
        System.out.println(student.name);
        System.out.println("Year: "+student.year.getName()+" | Balance: "+student.balance+"£"+" | Bank Balance: "+student.bankBalance);
        System.out.println("ID: "+Main.sng.UniversityManagement.University.IDList.get(student.name));
        for(Courses course:student.coursesEnrolled) {
            course.printCourseInfo();
        }
    }
    public void printInfo(Student student) {
        System.out.println(student.name);
        ConsoleOutputFunctions.printBreak();
        System.out.print(student.year.getName());
    }
}
