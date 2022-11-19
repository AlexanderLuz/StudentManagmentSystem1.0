import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static final Singleton sng = new Singleton();
    public static void main(String[] args) throws FileNotFoundException {
//        RandomStudentCSVTest test = new RandomStudentCSVTest();
//        test.createRandomStudents(1);
          sng.FileIO.StudentSheetFileReader();
//        sng.UniversityManagement.printAndWriteStudentsToUniversity();
//        do {
//            sng.StudentManagement.performStudentLoginAndActionProcedure();
//        }
//        while(sng.InputMethods.askForContinue("Is there another Student that wants to login?", 1));
    }
}
