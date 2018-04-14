package myApple;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class AppleDataAnalyzer {
    ArrayList<Apple> appleData;

    AppleDataAnalyzer() {
        this.appleData = new ArrayList<Apple>();
    }

    public static void main(String[] args) {
        AppleDataAnalyzer ada = new AppleDataAnalyzer();
        ada.loadData(args[0]);
        System.out.println("＊＊＊読み込みデータ＊＊＊");
        ada.printData();

        ada.addData("宮城", 3520);
        ada.addData("富山", 1560);
        System.out.println("＊＊＊データ追加結果＊＊＊");
        ada.printData();

        Collections.sort(ada.appleData);
        System.out.println("＊＊＊データソート結果＊＊＊");
        ada.printData();
        ada.printDataToFile("output.txt");
    }

    void loadData(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] divide = line.split("\\s");
                appleData.add(new Apple((divide[0]), Integer.parseInt(divide[1])));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    void printData() {
        for (Apple apple : appleData) {
            System.out.println(apple.place + " " + apple.amount);
        }
    }

    void addData(String place, int amount) {
        appleData.add(new Apple(place, amount));
    }


    void printDataToFile(String file) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            for (Apple apple : appleData) {
                writer.println(apple.place + " " + apple.amount);
            }
            writer.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
