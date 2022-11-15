import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class University {
    public static HashMap<Integer, Student> StudentList = new HashMap<Integer, Student>();
    public static HashMap<String, Integer> IDList = new HashMap<String, Integer>();
    public static void putStudentInUniversity(Student student) {
        Integer placeholderID = ID.getID(student.year.getYear());
        StudentList.put(placeholderID, student);
        IDList.put(student.name, placeholderID);
    }
    private static class ID {
        private static final AtomicInteger startingId = new AtomicInteger(1000);
        public static Integer getID(int year) {
            Integer id = startingId.incrementAndGet();
            id = id + (year*10000);
            return id;
        }
    }
}
