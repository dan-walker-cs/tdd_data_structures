package stacks;

import java.util.EmptyStackException;

public class MaxStack extends ListStack<Integer> {

    ListStack<Integer> stack;
    Integer max;

    public MaxStack() {
        this.stack = new ListStack<>();
        this.max = null;
    }

    public void push(int data) {
        if(max == null || data > max) {
            max = data;
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

    public Integer max() throws EmptyStackException {
        if(max == null) {
            throw new EmptyStackException();
        }
        return max;
    }
}
