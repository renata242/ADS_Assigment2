package com.company;
import java.util.NoSuchElementException;
import java.util.Arrays;


public class Heap<T extends Comparable<T>>{
        private T[] heap;
        private int size;
        private int capacity;
        private boolean min;

        public Heap() {
            this(50);
        }

        public Heap(int capacity) {
            this.capacity = capacity;
            size = 0;
            heap = (T[]) new Comparable[this.capacity];
        }

        public int size() {
            return size;
        }

        public boolean empty() {
            if (0 == this.size) return true;
            return false;
        }

        public void insert(T element) {
            if (this.size >= heap.length - 1) {
                heap = this.resize();
            }
            heap[size] = element;
            size++;
            heapifyUp();
        }

        private T[] resize() {
            return Arrays.copyOf(heap, heap.length + capacity);
        }

        private void heapifyUp() {
            int index = size - 1;
            if (min) {
                while (hasParent(index) && (parent(index).compareTo(heap[index]) > 0)) {
                    swap(index, parentIndex(index));
                    index = parentIndex(index);
                }
            } else {
                while (hasParent(index) && (parent(index).compareTo(heap[index]) < 0)) {
                    swap(index, parentIndex(index));
                    index = parentIndex(index);
                }
            }
        }

        private boolean hasParent(int index) {
            return index > 1;
        }

        //    private int leftChildIndex(int index)
//    {
//        return index * 2;
//    }
//
//    private int rightChildIndex(int index)
//    {
//        return index * 2 + 1;
//    }
//    public T getMin(){
//            return get(1);
//    }
//
//    private T get(int index){
//            return (T) heap[index];
//    }
//
//    public void Compare(int index){
//            T left = get(leftChildIndex(index));
//            T right = get(rightChildIndex(index));
//            if (left.compareTo(right)>0){
//
//            }
//    }
        private int parentIndex(int i) {
            return i / 2;
        }

        private T parent(int i) {
            return heap[parentIndex(i)];
        }

        public T extractMin() {
            if (empty()) System.out.println("Empty heap");
            T root = heap[0];
            swap(size() - 1, 0);
            heap[size() - 1] = null;
            size--;
            heapifyDown();
            return root;
        }

        private void heapifyDown() {
            int parent = 0;
            int leftChild = 2 * parent;
            int rightChild = 2 * parent + 1;
            int choice = Compare(leftChild, rightChild);
            while (choice != -1) {
                swap(choice, parent);
                parent = choice;
                choice = Compare(2 * choice + 1, 2 * choice + 2);
            }
        }

        private int Compare(int leftChild, int rightChild) {
            if (leftChild >= capacity || heap[leftChild] == null) return -1;
            if ((heap[rightChild] == null) || (heap[leftChild].compareTo(heap[rightChild]) <= 0))
                return leftChild;
            return rightChild;
        }

        public T getMin() {
            return get(1);
        }

        private T get(int index) {
            return (T) heap[index];
        }

        //        public Compare(int index){
//                T left = get(leftChildIndex(index));
//                T right = get(rightChildIndex(index));
//                if (left.compareTo(right)>0){
//                    return left;
//                }
//            return rightChildIndex;
//        }
        private void swap(int child, int parent) {
            T t = heap[child];
            heap[child] = heap[parent];
            heap[parent] = t;
        }

        public static void main(String[] args) {
            Heap h = new Heap(15);
            h.insert(19);
            h.insert(5);
            h.insert(10);
            h.insert(2);
            h.insert(8);
            h.insert(6);
            h.insert(3);
            System.out.println("The min value is "
                    + h.getMin());
            System.out.println("The extracted root value is "
                    + h.extractMin());
        }
    }