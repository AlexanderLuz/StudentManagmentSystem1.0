import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

    private final String Path = "StudentSheet.csv";

    private BufferedReader reader;
    private String line = "";

    public void StudentSheetFileIO() {
        try {
            reader = new BufferedReader(new FileReader(Path));

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

}
