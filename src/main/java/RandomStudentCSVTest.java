import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomStudentCSVTest {
    private final Random rnd = new Random();
    private final Faker faker = new Faker();
    private final Random rand = new Random();

    public void createRandomStudents(int amount) {
        for (int i = 0; i < amount; i++) {
            Courses[] courseList = Courses.values();
            ArrayList<Courses> coursesArrayList = new ArrayList<>(Arrays.asList(courseList));
            coursesArrayList.remove(Courses.INTRODUCTORY_COURSE);
            String name = faker.name().firstName();
            int balance = (rnd.nextInt(5000 + 1 - 1000) + 1000);
            int year = (rnd.nextInt(4 + 1 - 1) + 1);
            int bankBalance = (rnd.nextInt(25000 + 1 - 100) + 100);
            Student student = new Student(name,balance,year,bankBalance);
            student.coursesEnrolled.add(Courses.INTRODUCTORY_COURSE);
            student.coursesEnrolled.add(Courses.getEnum(rand.nextInt(coursesArrayList.size())));
            Main.sng.FileIO.StudentSheetFileWriter(student);
        }
    }
}
