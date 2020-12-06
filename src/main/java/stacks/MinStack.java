package stacks;

import java.util.EmptyStackException;

public class MinStack extends ListStack {

    ListStack<Integer> stack;
    Integer min;

    public MinStack() {
        this.stack = new ListStack<Integer>();
        this.min = null;
    }

    public void push(int data) {
        if(min == null || data < min) {
            min = data;
        }
        stack.push(data);
    }

    public Integer peek() {
        return (Integer) stack.peek();
    }

    public Integer pop() {
        return (Integer) stack.pop();
    }

    public int getSize() {
        return stack.getSize();
    }

    public Integer min() throws EmptyStackException {
        if(min == null) {
            throw new EmptyStackException();
        }
        return min;
    }
}
