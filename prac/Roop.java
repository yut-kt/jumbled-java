import java.io.*;

public class Roop {
    public static void main(String[] args) {
        BufferedReader read = new BufferedReader(new InputStreamReader (System.in));
        try {
            System.out.print('>');
            String x = read.readLine();
            while (!x.equals("EXIT")) {
                if (x.equals("")) {
                    System.out.println("未入力です。");
                } else {
                    System.out.println(x);
                }
                System.out.print('>');
                x = read.readLine();
            }
        } catch(IOException ex) {
            System.out.println(ex);
        }
    }
}
