package myApple;

public class Apple implements Comparable<Apple> {
    String place;
    int amount;

    Apple(String place, int amount) {
        this.place = place;
        this.amount = amount;
    }

    public int compareTo(Apple o1) {
        int operand = o1.amount;
        if (this.amount < operand) {
            return 1;
        } else if (this.amount > operand) {
            return -1;
        } else {
            return 0;
        }
    }
}
