import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClimateTester {
    public static void main(String[] args) {
        System.out.println("日本の平均気温からの偏差の推移");
        ClimateBase cl = new ClimateBase(args[0]); //日本の平均気温
        cl.printAllData();
        cl.yearAverage(1913);
        cl.drawTempertureGraph(0.01, 1913);
        cl.findHighestTemperature();
        System.out.println("");

        System.out.println("東京の気温と降水量");
        ClimateTokyo tc = new ClimateTokyo(args[1], args[2]); //東京の気温と降水量
        tc.printAllData();
        tc.yearAverage(1913, "temperature");
        tc.yearAverage(1913, "rain");
        tc.drawTempertureGraph(1, 1913);
        tc.drawRainGraph(10, 1913);
        tc.findHighestTemperature();
        tc.findHighestRainfall();

        System.out.println("");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("東京の気候を分析してみよう");
        while (true) {
            try {
                System.out.println("年を入力->");
                int year = Integer.parseInt(reader.readLine());
                System.out.println("データを入力-> 1.気温 2.降水量");
                int type = Integer.parseInt(reader.readLine());
                System.out.println("分析方法を入力-> 1.データ出力 2.平均 3.グラフ表示");
                int analysis = Integer.parseInt(reader.readLine());

                switch (type) { //データタイプ
                    case 1: //気温
                    if (analysis == 1) {
                        AnnualData ad = tc.getYearTemperatureData(year);
                        ad.printAnnualData();
                    } else if (analysis == 2) {
                        AnnualData ad = tc.getYearTemperatureData(year);
                        tc.yearAverage(year, "temperature");
                    } else if (analysis == 3) {
                        tc.drawTempertureGraph(1, year);
                    } else {
                        System.out.println("分析方法を入力してください");
                    }
                    break;
                    case 2: //降水量
                    if (analysis == 1) {
                        AnnualData ad = tc.getYearRainData(year);
                    } else if (analysis == 2) {
                        tc.yearAverage(year, "rain");
                    } else if (analysis == 3) {
                        tc.drawRainGraph(10, year);
                    } else {
                        System.out.println("分析方法を入力してください");
                    }
                    break;
                    default:
                    System.out.println("データの種類を指定してください");
                    break;
                }
            } catch(Exception e) {
                System.out.println(e);
            }
        }

    }
}
