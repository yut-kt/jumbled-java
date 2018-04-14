public class Hogemon extends Monster implements ShowStatus {
    
    Hogemon(int h, int p, int d, String n) {
        super(h, p, d, n);
    }

    public int damegeTo(Monster c) {
        int num = this.power - c.defence > 0 ? this.power - c.defence : 1;
        return num;
    }

    public String show() {
        return (this.name + '[' + this.hp + ']');
    }
}
