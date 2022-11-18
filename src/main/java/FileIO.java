import com.opencsv.CSVWriter;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FileIO {

    public void StudentSheetFileReader() {
        try {
            String path = "src/main/resources/NewStudentSheet.csv";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int balance = Integer.parseInt(values[1].replaceAll("\"", ""));
                int year = Integer.parseInt(values[2].replaceAll("\"", ""));
                int bankBalance = Integer.parseInt(values[3].replaceAll("\"", ""));
                Student student = new Student(name,balance,year,bankBalance);
                Main.sng.UniversityManagement.studentArrayList.add(student);
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
        String path = "src/main/resources/NewStudentSheet.csv";
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(path, true));
            String name = student.name;
            String balance = String.valueOf(student.balance).replaceAll("\"", "");
            String year = String.valueOf(student.year.getYear()).replaceAll("\"", "");
            String bankBalance = String.valueOf(student.bankBalance).replaceAll("\"", "");
            String[] values = new String[] {name,balance,year,bankBalance};
            writer.writeNext(values);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
