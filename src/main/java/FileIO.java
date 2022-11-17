import com.opencsv.CSVWriter;

import java.io.*;

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
    public void StudentSheetFileWriter() throws IOException {
        String path = "src/main/resources/StudentSheet.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(path, true));
    }
}
