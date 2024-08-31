package ADT;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Author: Heng Han Yee
 *         Ho Zhi Xuen
 *         Lee Zun Wei
 *         Phoon Chong Seng
 */

/**
 * A doubly-linked list implementation of a queue that allows for efficient 
 * addition and removal of elements from both ends. This implementation 
 * also supports common queue operations like enqueue, dequeue, and peek.
 * 
 * @param <E> the type of elements held in this queue
 */
public class DoublyLinkedQueue<E> implements Queue<E>, Iterable<E> {
    /**
     * A private inner class representing a node in the doubly-linked list.
     * Each node stores a reference to the data and pointers to the next
     * and previous nodes in the list.
     */
    private class Node {
        E data;
        Node next;
        Node prev;

        /**
         * Constructs a new node with the given data.
         * 
         * @param data the data to be stored in this node
         */
        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    // Construct empty queue
    public DoublyLinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Adds an element to the end of the queue.
     * 
     * @param item the element to be added
     */
    public void enqueue(E item) {
        Node newNode = new Node(item);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }


     /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public E dequeue() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        E data = front.data;
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.next;
            front.prev = null;
        }
        size--;
        return data;
    }
    
    /**
     * Returns, but does not remove, the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public E peek() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    /**
     * Returns, but does not remove, the element at the rear of the queue.
     * 
     * @return the element at the rear of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public E peekLast() {
        if (rear == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return rear.data;
    }

    //Add element to the front of the queue
    public void addFirst(E item) {
        Node newNode = new Node(item);
        if (front == null) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    //Removes and returns the element at the rear of the queue.
    public E removeLast() {
        if (rear == null) {
            throw new IllegalStateException("Queue is empty");
        }
        E data = rear.data;
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            rear = rear.prev;
            rear.next = null;
        }
        size--;
        return data;
    }

    // Checks if the queue is empty.
    public boolean isEmpty() {
        return size == 0;
    }
    
    //Returns the number of elements in the queue.
    public int size() {
        return size;
    }

    // Returns, but does not remove, the last element in the queue.
    // This method is equivalent to {@link #peek()}.
    public E getLast() {
        return peekLast();
    }

    /**
     * Checks if the queue contains the specified element.
     * 
     * @param data the element to check for
     * @return true if the queue contains the element, false otherwise
     */
    public boolean contains(E data) {
        Node current = front;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from the queue, if it is present.
     * 
     * @param item the element to be removed
     * @return true if the element was removed, false otherwise
     */
    public boolean remove(E item) {
        Node current = front;
        while (current != null) {
            if (current.data.equals(item)) {
                if (current == front && current == rear) {
                    front = null;
                    rear = null;
                } else if (current == front) {
                    front = front.next;
                    if (front != null) {
                        front.prev = null;
                    }
                } else if (current == rear) {
                    rear = rear.prev;
                    if (rear != null) {
                        rear.next = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //Initialize the queue by resetting it to an empty state
    @Override
    public void initialize() {
        front = null;
        rear = null;
        size = 0;
    }

    //Clears the queue
    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    //Adds element to the end of the queue
    @Override
    public boolean offer(E item) {
        enqueue(item);
        return true;
    }

    /**
     * Removes and returns the element at the front of the queue,
     * or returns null if the queue is empty.
     * 
     * @return the element at the front of the queue, or null if the queue is empty
     */
    @Override
    public E poll() {
        if (front == null) {
            return null;
        }
        return dequeue();
    }

    /**
     * Returns, but does not remove, the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     */
    @Override
    public E element() {
        return peek();
    }


    /**
     * Returns an array containing all of the elements in the queue in proper sequence.
     * 
     * @return an array containing all of the elements in the queue
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = front;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * Returns an iterator over the elements in this queue in proper sequence.
     * 
     * @return an iterator over the elements in this queue
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = front;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
