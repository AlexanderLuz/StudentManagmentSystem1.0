public class Main {
    public static final Singleton sng = new Singleton();
    public static void main(String[] args) {
        sng.FileIO.StudentSheetFileReader();
        sng.UniversityManagement.instantiateStudents();
        do {
            sng.StudentManagement.performStudentLoginAndActionProcedure();
        }
        while(sng.InputMethods.askForContinue("Is there another Student that wants to login?", 1));
    }
}