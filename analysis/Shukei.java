public class Shukei {
    Kamoku[] klist;

    Shukei() {
        this.klist = null;
    }
    Shukei(Kamoku[] kl) {
        this.klist = kl;
    }
    double heikin() {
        int sum = 0;
        for (Kamoku k : klist) {
            sum += k.getScore();
        }
        return (double)sum / klist.length;
    }
    void ichiran() {
        for (Kamoku k : klist) {
            System.out.println(k.getName() + " " + k.getScore());
        }
    }
}
