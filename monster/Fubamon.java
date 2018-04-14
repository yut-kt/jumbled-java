public class Fubamon extends Monster implements ShowStatus {

    private int count = 0;

    Fubamon(int h, int p, int d, String n) {
        super(h, p, d, n);
    }

    public int damegeTo(Monster c) {
        count++;
        int num;
        if (count % 2 == 0) {
            num = 2 * this.power - c.defence > 0 ? 2 * this.power - c.defence : 1;
        } else {
            num = this.power - c.defence > 0 ? this.power - c.defence : 1;
        }
        return num;
    }

    public String show() {
        return (this.name + '[' + this.hp + ']');
    }
}
