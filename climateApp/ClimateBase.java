import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClimateBase implements MyFileManager, DataAnalyzer {
    ArrayList <AnnualData> annual_temperature = new ArrayList <AnnualData> ();

    ClimateBase(String filename) {
        annual_temperature = fileLoader(filename);
    }

    public ArrayList <AnnualData> fileLoader (String filename) {
        ArrayList <AnnualData> data = new ArrayList <AnnualData> ();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(new AnnualData(line));
            }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
            return data;
    }

    public void printAllData() {
        for (AnnualData data : annual_temperature) {
            data.printAnnualData();
        }
    }

    public double calAverage(ArrayList <DataObj> array) {
        double sum = 0;
        for (DataObj data : array) {
            sum += data.value;
        }
        return sum / array.size();
    }

    public void drawGraph(double measure, AnnualData ad) {
        for (DataObj data : ad.monthList) {
            System.out.print(data.name);
            double measure2 = measure;
            while (measure2 < data.value) {
                System.out.print("*");
                measure2 += measure;
            }
        }
    }

    public DataObj findMax(ArrayList <DataObj> array) {
        DataObj max = new DataObj("max", -93.2); //世界最低気温
        for (DataObj data : array) {
            if (max.value < data.value) {
                max = data;
            }
        }
        return max;
    }

    protected void yearAverage(int year) {
        System.out.println(year + "年の平均気温:" + calAverage(getYearTemperatureData(year).monthList));
    }

    protected void drawTempertureGraph(double measure, int year) {
        drawGraph(measure, getYearTemperatureData(year));
    }

    protected AnnualData getYearTemperatureData(int year) {
        for (AnnualData data : annual_temperature) {
            if (data.year == year) {
                return data;
            }
        }
        return null;
    }

    protected void findHighestTemperature() {
        int max_year = 0;
        DataObj max = new DataObj("max",-93.2);
        for (AnnualData data : annual_temperature) {
            DataObj temp = findMax(data.monthList);
            if (max.value < temp.value) {
                max_year = data.year;
                max = temp;
            }
        }
        System.out.print("最高平均気温記録: " + max_year + "年 " + max.name + " " + max.value + "℃");
    }
}
