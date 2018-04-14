public class ScoreSystem {
    public static void main(String[] args) {
        Kamoku[] ks = new Kamoku[3];
        ks[0] = new Kamoku("英語", 63);
        ks[1] = new Kamoku("数学", 92);
        ks[2] = new Kamoku("理科", 75);
        ShukeiAdapter adapter = new ShukeiAdapter(ks);
        System.out.println("*****Shukeiクラスから継承*****");
        adapter.ichiran();
        System.out.println("平均 : " + adapter.heikin());
        System.out.println("*****Analysisインタフェースを実装*****");
        adapter.print();
        System.out.println("平均(四捨五入) : " + adapter.average());
    }
}
