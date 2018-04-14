public class ShukeiAdapter extends Shukei implements Analysis {
    double av;

    ShukeiAdapter(Kamoku[] kl) {
        super(kl);
    }
    
    public double average() {return Math.round(super.heikin());}
    public void print() {super.ichiran();}
}
