public class Age {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("引数に年齢を指定してください");
        } else {
            double sum = 0;
            for (String age : args) {
                sum += Integer.parseInt(age);
            }
            System.out.println("平均年齢 : " + sum / args.length);
        }
    }
}
