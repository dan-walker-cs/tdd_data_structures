package heaps;

import java.util.NoSuchElementException;

public class MinHeap {

    int[] heap;
    int maxSize;
    int currentSize;

    public MinHeap() {
        this.maxSize = 5;
        this.currentSize = 0;
        this.heap = new int[maxSize];
    }

    MinHeap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.heap = new int[maxSize];
    }

    private void minHeapify(int currentIndex) {
        if(!isLeaf(currentIndex)) {
            int leftChild = leftChildIndex(currentIndex);
            int rightChild = rightChildIndex(currentIndex);
            if(heap[leftChild] < heap[currentIndex] || heap[rightChild] < currentIndex) {
                if(heap[leftChild] < heap[rightChild]) {
                    swap(leftChild, currentIndex);
                    minHeapify(leftChild);
                } else {
                    swap(rightChild, currentIndex);
                    minHeapify(rightChild);
                }
            }
        }
        heap[currentSize] = 0;
    }

    public int remove() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        int root = heap[0];
        heap[0] = heap[--currentSize];

        minHeapify(0);

        return root;
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public void insert(int data) throws NoSuchElementException {
        if(isFull()) {
            throw new NoSuchElementException();
        }

        int currentIndex = currentSize;
        heap[currentIndex] = data;

        while(heap[currentIndex] < heap[parentIndex(currentIndex)]) {
            swap(currentIndex, parentIndex(currentIndex));
            currentIndex = parentIndex(currentIndex);
        }
        currentSize++;
    }

    private boolean isLeaf(int nodeIndex) {
        if(nodeIndex >= currentSize/2 && nodeIndex < maxSize) {
            return true;
        }
         return false;
    }

    private int parentIndex(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    private int leftChildIndex(int nodeIndex) {
        return nodeIndex * 2 + 1;
    }

    private int rightChildIndex(int nodeIndex) {
        return nodeIndex * 2 + 2;
    }

    private boolean isFull() {
        if(currentSize == maxSize) {
            return true;
        }
        return false;
    }

    private boolean isEmpty() {
        if(currentSize == 0) {
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
