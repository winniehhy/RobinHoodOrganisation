package ADT;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple implementation of an ArrayList.
 * 
 * @param <E> the type of elements in this list
 * 
 * Authors: Ho Zhi Xuen
 */
public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty ArrayList with the default capacity.
     */
    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) elements[currentIndex++];
            }
        };
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e the element to be appended
     * @return true if appended
     */
    @Override
    public boolean add(E e) {
        checkCapacity(size + 1);
        elements[size++] = e;
        return true;
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
        // Nullify all references to allow garbage collection
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elements[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * 
     * Check if the list has enough capacity to accommodate the specified number of elements.
     *
     * @param minCapacity the minimum capacity required
     */
    private void checkCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + 10; // Increase capacity by 10
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Checks if the specified index is within the valid range.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
