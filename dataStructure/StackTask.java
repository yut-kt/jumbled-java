import java.util.*;

public class StackTask extends Stack implements Top, Push, Pop {
    public static void main(String[] args) {
        StackTask st = new StackTask("Stack");
        st.push("a");
        st.push("b");
        System.out.println(st.top());
        st.push("c");
        st.pop();
        st.pop();
        st.push("d");
    }

    StackTask(String name) {
        super.stack = new ArrayList<String> ();
        super.setName(name);
    }

    void printStack() {
        System.out.print(getName() + " [");
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i));
            if (stack.size() != i + 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public void push(String element) {
        if (stack.isEmpty()) {
            stack.add(element);
        } else {
            stack.add(0, element);
        }
        printStack();
    }

    public void pop() {
        if (stack.isEmpty()) {
            System.out.println("empty");
        } else {
            stack.remove(0);
            printStack();
        }
    }

    public String top() {
        if (stack.isEmpty()) {
            return "empty";
        } else {
            return stack.get(0);
        }
    }
}
