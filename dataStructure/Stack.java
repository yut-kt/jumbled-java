import java.util.*;

abstract class Stack {
    private String name;
    ArrayList<String> stack;

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    abstract void printStack();
}
