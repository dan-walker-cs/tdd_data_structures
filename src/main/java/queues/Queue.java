package queues;

import java.util.NoSuchElementException;

public class Queue<T> {

    QueueNode head;
    QueueNode tail;
    int length;

    private class QueueNode<T> {
        private T data;
        private QueueNode next;

        QueueNode() {
            this.data = null;
            this.next = null;
        }

        QueueNode(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        this.head = new QueueNode();
        this.tail = new QueueNode();
        this.length = 0;
        head.next = tail;
    }

    public void enqueue(T data) {
        QueueNode newNode = new QueueNode(data);
        if(head.next == tail) {
            head.next = newNode;
            newNode.next = tail;
        } else {
            QueueNode temp = head.next;
            head.next = newNode;
            newNode.next = temp;
        }
        length++;
    }

    public <T> Object peek() throws NoSuchElementException {
        if(this.isEmpty()) {
            throw new NoSuchElementException();
        }
        QueueNode iterator = head.next;
        while(iterator.next != tail) {
            iterator = iterator.next;
        }
        return iterator.data;
    }

    public <T> Object dequeue() throws NoSuchElementException {
        if(this.isEmpty()) {
            throw new NoSuchElementException();
        }
        // find target node with iterator
        QueueNode iterator = head.next;
        // track unlink position with temp
        QueueNode temp = head;
        while(iterator.next != tail) {
            iterator = iterator.next;
            temp = temp.next;
        }
        temp.next = tail;
        length--;

        return iterator.data;
    }

    public boolean isEmpty() {
        if(length == 0) {
            return true;
        }
        return false;
    }

    public int getLength() {
        return length;
    }

    public String toString() {
        if(head.next == tail) {
            return "Empty Queue";
        }

        String template = "head -> ";
        QueueNode iterator = head.next;
        while(iterator.next != null) {
            template += iterator.data.toString();
            template += " -> ";
            iterator = iterator.next;
        }
        template += "tail";
        return template;
    }
}
