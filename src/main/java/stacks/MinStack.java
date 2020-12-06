package stacks;

public class MinStack extends ListStack {

    ListStack<Integer> stack;
    Integer min;

    MinStack() {
        this.stack = new ListStack<Integer>();
        this.min = null;
    }

    public void push(int data) {
        if(min == null || data < min) {
            min = data;
        }
        stack.push(data);
    }

    public Integer min() {
        return null;
    }
}
