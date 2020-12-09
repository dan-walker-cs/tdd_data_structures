package heaps;

import java.util.NoSuchElementException;

public class MaxHeap {

    int[] heap;
    int currentSize;
    int maxSize;

    public MaxHeap() {
        this.maxSize = 5;
        this.currentSize = 0;
        this.heap = new int[maxSize];
    }

    MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.heap = new int[maxSize];
    }

    public int remove() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        int root = heap[0];
        heap[0] = heap[--currentSize];
        heap[currentSize] = 0;

        maxHeapify(0);

        return root;
    }

    private void maxHeapify(int currentIndex) {
        if(!isLeaf(currentIndex)) {
            int leftChild = leftChildIndex(currentIndex);
            int rightChild = rightChildIndex(currentIndex);
            if(heap[leftChild] > heap[currentIndex] || heap[rightChild] > heap[currentIndex]) {
                if(heap[leftChild] > heap[rightChild]) {
                    swap(leftChild, currentIndex);
                    currentIndex = leftChild;
                } else {
                    swap(rightChild, currentIndex);
                    currentIndex = rightChild;
                }
                maxHeapify(currentIndex);
            }
        }
    }

    public void insert(int data) throws NoSuchElementException {
        if(isFull()) {
            throw new NoSuchElementException();
        }
        heap[currentSize] = data;
        int currentIndex = currentSize;
        currentSize++;

        while(heap[currentIndex] > heap[parentIndex(currentIndex)]) {
            swap(currentIndex, parentIndex(currentIndex));
            currentIndex = parentIndex(currentIndex);
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
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
        if(currentIndex > currentSize/2 && currentIndex < currentSize) {
            return true;
        }
        return false;
    }

    public String toString() {
        String template = "[";
        for(int i = 0; i < maxSize-1; i++) {
            template += heap[i] + ", ";
        }
        template += heap[maxSize-1] + "]";
        return template;
    }
}
