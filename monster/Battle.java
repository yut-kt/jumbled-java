 public class Battle {
    public static void main(String[] args) {
        Hogemon hoge = new Hogemon(10, 5, 2, "ホゲリン");
        Fubamon fuba = new Fubamon(4, 4, 6, "フバオ");

        while (hoge.getHp() > 0 && fuba.getHp() > 0) {
            hoge.attack(fuba);
            fuba.attack(hoge);
            System.out.println(hoge.show() + ", " + fuba.show());
        }
        if (hoge.getHp() < 1) {
            hoge.run();
        } else {
            fuba.run();
        }
    }
 }
