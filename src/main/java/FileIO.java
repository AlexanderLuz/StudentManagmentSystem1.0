import com.opencsv.CSVWriter;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void StudentSheetFileWriter(Student student) throws IOException {
        String path = "src/main/resources/StudentSheet.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(path, true));
        String[] studentValues = new String[4];
        studentValues[0] = student.name;
        studentValues[1] = String.valueOf(student.balance);
        studentValues[2] = String.valueOf(student.year.getYear());
        studentValues[3] = String.valueOf(student.bankBalance);
        writer.writeNext(studentValues);
        writer.close();
    }
}
