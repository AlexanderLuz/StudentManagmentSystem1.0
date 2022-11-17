import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class University {
    private static final Courses[] CoursesList = Courses.values();
    private static AtomicInteger startingID = new AtomicInteger(1000);
    public static HashMap<Integer, Student> StudentList = new HashMap<Integer, Student>();
    public static HashMap<String, Integer> IDList = new HashMap<String, Integer>();
    public static HashMap<Courses, Integer> CoursesCapacities = new HashMap<Courses, Integer>();
    public static HashMap<Courses, Boolean> CoursesFilled = new HashMap<Courses, Boolean>();
    public static HashMap<Courses, ArrayList<Student>> CoursesStudentList = new HashMap<Courses, ArrayList<Student>>();

    static {
        for(Courses course:CoursesList) {
            CoursesCapacities.put(course, 0);
            CoursesFilled.put(course, false);
            CoursesStudentList.put(course, new ArrayList<>());
        }
    }

    public static void putStudentInUniversity(Student student) {
        if(!StudentList.containsValue(student)) {
            Integer placeholderID = getID(student.year.getYear());
            StudentList.put(placeholderID, student);
            IDList.put(student.name, placeholderID);
        }
    }
    private static Integer getID(int year) {
        int id = startingID.incrementAndGet();
        id = id + (year*10000);
        return id;
    }
    public static void putStudentInCourse(Student student, Courses courseEnum) {
        CoursesCapacities.put(courseEnum,CoursesCapacities.get(courseEnum)+1);
        CoursesStudentList.get(courseEnum).add(student);
        if(CoursesCapacities.get(courseEnum) == courseEnum.getCapacity()) {
            CoursesFilled.put(courseEnum, true);
        }
    }
    public static void printStudentInCoursesList(Courses courseEnum) {
        courseEnum.printCourseInfo();
        for(Student student:CoursesStudentList.get(courseEnum)) {
            student.printInfo();
        }
    }
}
