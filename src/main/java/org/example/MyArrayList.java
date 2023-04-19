package org.example;

import java.util.Arrays;

public class MyArrayList<T> {
    private T[] elements = null;
    private int capacity;
    private int top;

    public MyArrayList() {
        this.capacity = 8;
        this.top = -1;
        elements = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int size() {
        return top + 1;
    }

    public boolean add(T element) {
        if (isFull()) {
            try {
                this.capacity *= 2;
                elements = Arrays.copyOf(elements, capacity);
            } catch (OutOfMemoryError e) {
                return false;
            }
        }
        this.elements[++top] = element;

        return true;
    }

    public T remove(int index) {
        try {
            T removeElement = elements[index];
            for (int i = index; i < size() - 1; i++)
                elements[i] = elements[i + 1];

            top--;
            return removeElement;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public T get(int index) {
        try {
            return this.elements[index];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean contains(T element) {
        for (int i = 0; i < size(); i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size(); i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        this.top = -1;
    }
}
