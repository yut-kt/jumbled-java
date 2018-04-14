import java.io.*;
import java.util.*;

public class Today {
    public static void main(String[] args) {
        Calendar now  = Calendar.getInstance();
        int m = now.get (now.MONTH) + 1;
        int d = now.get(now.DATE);
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        try {
        System.out.println("今日は何月何日でしょう？");
        System.out.print("何月？＝＝＞");
        int month = Integer.parseInt(read.readLine());
        System.out.print("何日？＝＝＞");
        int date = Integer.parseInt(read.readLine());
        if (m == month && d == date) {
            System.out.println("正解！！");
        } else {
            System.out.println("間違っています");
        }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
