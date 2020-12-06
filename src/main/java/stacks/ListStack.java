package stacks;

import java.util.EmptyStackException;

public class ListStack<T> {

    ListNode top;
    // testing purposes
    int size;

    private class ListNode<T> {
        private T data;
        private ListNode next;

        ListNode() {
            this.data = null;
            this.next = null;
        }

        ListNode(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public ListStack() {
        this.top = new ListNode();
        this.size = 0;
    }

    public <T> Object pop() throws EmptyStackException {
        if(top.next == null) {
            throw new EmptyStackException();
        }

        // unlink
        ListNode temp = top.next;
        top.next = temp.next;
        size--;

        return temp.data;
    }

    public <T> Object peek() throws EmptyStackException {
        if(top.next == null) {
            throw new EmptyStackException();
        }
        return top.next.data;
    }

    public void push(T data) {
        ListNode newNode = new ListNode(data);

        // empty stack
        if (top.next == null) {
            top.next = newNode;
        } else {
            // link nodes
            ListNode temp = top.next;
            top.next = newNode;
            newNode.next = temp;
        }

        // testing purposes
        size++;
    }

    // testing purposes
    public int getSize() {
        return size;
    }

    public String toString() {
        if (top.next == null) {
            return "Stack Empty";
        }
        String template = "";
        ListNode temp = top.next;

        template += "top: ";
        while(temp != null) {
            template += temp.data;
            template += "\nv\n";
            temp = temp.next;
        }

        template += "null";
        return template;
    }
}