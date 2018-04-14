import java.io.IOException;
import java.util.ArrayList;

public interface MyFileManager {
    ArrayList <AnnualData> fileLoader(String filename) throws IOException;
    void printAllData();
}
