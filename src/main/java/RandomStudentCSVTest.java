import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.Random;

public class RandomStudentCSVTest {
    private final Random rnd = new Random();
    private final Faker faker = new Faker();

    public void createRandomStudents(int amount) {
        for (int i = 0; i < amount; i++) {
            String name = faker.name().firstName();
            int balance = (rnd.nextInt(5000 + 1 - 1000) + 1000);
            int year = (rnd.nextInt(4 + 1 - 1) + 1);
            int bankBalance = (rnd.nextInt(25000 + 1 - 100) + 100);
            Student student = new Student(name,balance,year,bankBalance);
            Main.sng.UniversityManagement.studentArrayList.add(student);
            Main.sng.FileIO.StudentSheetFileWriter(new Student(name,balance,year,bankBalance));
        }
    }
}
