import java.util.ArrayList;
import java.util.HashMap;


public class University {
    public HashMap<Integer, Student> StudentList = new HashMap<>();
    public HashMap<String, Integer> IDList = new HashMap<>();
    public HashMap<Courses, Integer> CoursesCapacities = new HashMap<>();
    public HashMap<Courses, Boolean> CoursesFilled = new HashMap<>();
    public HashMap<Courses, ArrayList<Student>> CoursesStudentList = new HashMap<>();
    public final Courses[] CoursesList = Courses.values();
    public final int MasterPassword = 1728780524;

    University() {
        for(Courses course:CoursesList) {
            CoursesCapacities.put(course, 0);
            CoursesFilled.put(course, false);
            CoursesStudentList.put(course, new ArrayList<>());
        }
    }
}
