import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class University {
    private static AtomicInteger startingID = new AtomicInteger(1000);
    public static HashMap<Integer, Student> StudentList = new HashMap<Integer, Student>();
    public static HashMap<String, Integer> IDList = new HashMap<String, Integer>();
    public static void putStudentInUniversity(Student student) {
        Integer placeholderID = getID(student.year.getYear());
        StudentList.put(placeholderID, student);
        IDList.put(student.name, placeholderID);
    }
    private static Integer getID(int year) {
        int id = startingID.incrementAndGet();
        id = id + (year*10000);
        return id;
    }

}
