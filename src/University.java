import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class University {
    public HashMap<Integer, Student> StudentList = new HashMap<Integer, Student>();
    public HashMap<String, Integer> IDList = new HashMap<String, Integer>();
    public HashMap<Courses, Integer> CoursesCapacities = new HashMap<Courses, Integer>();
    public HashMap<Courses, Boolean> CoursesFilled = new HashMap<Courses, Boolean>();
    public HashMap<Courses, ArrayList<Student>> CoursesStudentList = new HashMap<Courses, ArrayList<Student>>();
    public final Courses[] CoursesList = Courses.values();
    public AtomicInteger startingID = new AtomicInteger(1000);


    University() {
        for(Courses course:CoursesList) {
            CoursesCapacities.put(course, 0);
            CoursesFilled.put(course, false);
            CoursesStudentList.put(course, new ArrayList<>());
        }
    }
}
