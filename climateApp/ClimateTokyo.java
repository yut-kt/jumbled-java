import java.util.ArrayList;

public class ClimateTokyo extends ClimateBase {
    ArrayList <AnnualData> annual_rainfall = new ArrayList <AnnualData> ();

    ClimateTokyo(String filename1, String filename2) {
        super(filename1);
        annual_rainfall = fileLoader(filename2);
    }

    void yearAverage(int year, String dataType) {
        if (dataType.equals("temperature")) {
            super.yearAverage(year);
        } else if (dataType.equals("rain")) {
            System.out.println(year + "年の平均降水量:" + calAverage(getYearRainData(year).monthList));
        } else {
            System.out.println("Wrong dataType");
        }
    }

    public void drawRainGraph(double measure, int year) {
        drawGraph(measure, getYearRainData(year));
    }

    AnnualData getYearRainData(int year) {
        for (AnnualData data : annual_rainfall) {
            if (data.year == year) {
                return data;
            }
        }
        return null;
    }

    void findHighestRainfall() {
        int max_year = 0;
        DataObj max = new DataObj("max",-93.2);
        for (AnnualData data : annual_rainfall) {
            DataObj temp = findMax(data.monthList);
            if (max.value < temp.value) {
                max_year = data.year;
                max = temp;
            }
        }
        System.out.print("最高平均降水量記録: " + max_year + "年 " + max.name + " " + max.value + "mm");
    }
}
