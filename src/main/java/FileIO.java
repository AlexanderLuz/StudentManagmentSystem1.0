import com.opencsv.CSVWriter;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FileIO {

    public void StudentSheetFileReader() {
        try {
            String path = "src/main/resources/StudentSheet.csv";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Main.sng.UniversityManagement.studentArrayList.add(new Student(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3])));
            }
            Main.sng.UniversityManagement.printAndWriteStudentsToUniversity();
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void StudentSheetFileWriter(Student student) {
        String path = "src/main/resources/StudentSheet.csv";
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(path, true));
            writer.writeNext(new String[] {student.name,String.valueOf(student.balance),String.valueOf(student.year.getYear()),String.valueOf(student.bankBalance)});
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
