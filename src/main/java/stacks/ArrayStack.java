package stacks;

public class ArrayStack {

    private int size;
    private int capacity;
    private int[] stack;
    private int top;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.top = 0;
        this.stack = new int[capacity];
    }

    public int pop() {
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int target = stack[--top];
        this.size--;

        return target;
    }

    public int peek(){
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return stack[--top];
    }

    public void push(int value) throws IllegalArgumentException {
        if(size == capacity) {
            increaseCapacity();
        }

        stack[top] = value;
        this.top++;
        this.size++;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    private void increaseCapacity() {
        this.capacity *= 2;
        int[] newStack = new int[capacity];
        for(int i = 0; i < size; i++) {
            newStack[i] = stack[i];
        }
        this.stack = newStack;
    }

    public String toString() {
        String template = "[";
        for(int i = 0; i < size; i++) {
            template += stack[i] + ", ";
        }
        template = template.substring(0, template.length() - 2);
        template += "]";

        return template;
    }
}
