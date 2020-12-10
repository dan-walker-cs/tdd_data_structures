package queues;

import java.util.NoSuchElementException;
import java.util.Optional;

public class PriorityQueue<T> {

    PQnode[] p_queue;
    int currentSize;
    int maxSize;

    private class PQnode<T> {
        T data;
        int priority;
        static final int MIN_PRIORITY = 1;

        PQnode(T data) {
            this.data = data;
            this.priority = MIN_PRIORITY;
        }

        PQnode(T data, int priority) {
            this.data = data;
            this.priority = priority;
        }

        public String toString() {
            String template = "(" + data.toString() + "," + priority + ")";
            return template;
        }
    }

    public PriorityQueue() {
        this.maxSize = 5;
        this.p_queue = new PQnode[maxSize];
        this.currentSize = 0;
    }

    PriorityQueue(int maxSize) {
        this.maxSize = maxSize;
        this.p_queue = new PQnode[maxSize];
        this.currentSize = 0;
    }

    public T remove() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        PQnode root = p_queue[0];
        p_queue[0] = p_queue[--currentSize];
        p_queue[currentSize] = null;

        maxHeapify(0);

        return (T)root.data;
    }

    private void maxHeapify(int currentIndex) {
        if(!isLeaf(currentIndex) && p_queue[currentIndex] != null) {
            int leftPriority = 0;
            int rightPriority = 0;
            if(p_queue[leftChildIndex(currentIndex)] == null) {
                leftPriority = Integer.MIN_VALUE;
            } else if(p_queue[rightChildIndex(currentIndex)] == null) {
                rightPriority = Integer.MIN_VALUE;
            } else {
                leftPriority = p_queue[leftChildIndex(currentIndex)].priority;
                rightPriority = p_queue[rightChildIndex(currentIndex)].priority;
            }

            if(leftPriority > p_queue[currentIndex].priority
                    || rightPriority > p_queue[currentIndex].priority) {
                if(leftPriority > rightPriority) {
                    swap(leftChildIndex(currentIndex), currentIndex);
                    currentIndex = leftChildIndex(currentIndex);
                } else {
                    swap(rightChildIndex(currentIndex), currentIndex);
                    currentIndex = rightChildIndex(currentIndex);
                }
                maxHeapify(currentIndex);
            }
        }
    }

    public void insert(T data) {
        insert(data, PQnode.MIN_PRIORITY);
    }

    public void insert(T data, int priority) throws NoSuchElementException {
        if(isFull()) {
            throw new NoSuchElementException();
        }
        int currentIndex = currentSize;
        PQnode newNode = new PQnode(data, priority);
        p_queue[currentIndex] = newNode;
        currentSize++;

        while(p_queue[currentIndex].priority > p_queue[parentIndex(currentIndex)].priority) {
            swap(currentIndex, parentIndex(currentIndex));
            currentIndex = parentIndex(currentIndex);
        }
    }

    private void swap(int index1, int index2) {
        PQnode temp = p_queue[index1];
        p_queue[index1] = p_queue[index2];
        p_queue[index2] = temp;
    }

    private boolean isEmpty() {
        if(currentSize == 0) {
            return true;
        }
        return false;
    }

    private boolean isFull() {
        if(currentSize == maxSize) {
            return true;
        }
        return false;
    }

    private int parentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }

    private int leftChildIndex(int currentIndex) {
        return currentIndex * 2 + 1;
    }

    private int rightChildIndex(int currentIndex) {
        return currentIndex * 2 + 2;
    }

    private boolean isLeaf(int currentIndex) {
        if(currentIndex > (currentSize / 2) && currentIndex < currentSize) {
            return true;
        }
        return false;
    }

    public String toString() {
        String template = "[";
        for(int i = 0; i < maxSize-1; i++) {
            template += p_queue[i] + ", ";
        }
        template += p_queue[maxSize-1] + "]";
        return template;
    }
}
