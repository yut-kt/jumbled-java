public abstract class Monster implements ShowStatus {
    protected int hp;
    protected int power;
    protected int defence;
    protected String name;

    Monster(int h, int p, int d, String n) {
        hp = h;
        power = p;
        defence = d;
        name = n;
    }

    //他のMonsterに与えるダメージを算出するメソッド
    protected abstract int damegeTo(Monster c);

    public void run() {
        System.out.println(this.name + "は逃げ出した。");
    }

    public void attack(Monster c) {
        int at = damegeTo(c);
        System.out.println(name + "のアタック！　" + c.name + "は" + at + "のダメージを受けた");
        c.hp -= at;
    }

    public int getHp() {
        return this.hp;
    }
}
