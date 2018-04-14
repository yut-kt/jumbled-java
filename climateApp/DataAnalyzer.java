import java.util.ArrayList;

public interface DataAnalyzer {
    double calAverage(ArrayList <DataObj> array);
    void drawGraph(double measure, AnnualData ad);
    DataObj findMax(ArrayList <DataObj> array);
}
