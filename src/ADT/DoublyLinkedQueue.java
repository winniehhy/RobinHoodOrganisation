package ADT;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * A doubly-linked list implementation of a queue that allows for efficient 
 * addition and removal of elements from both ends. This implementation 
 * also supports common queue operations like enqueue, dequeue, and peek.
 * 
 * @param <E> the type of elements held in this queue
 * 
 * Author: Heng Han Yee
 *         Ho Zhi Xuen
 *         Lee Zun Wei
 *         Phoon Chong Seng
 */
public class DoublyLinkedQueue<E> implements Queue<E>, Iterable<E> {
    /**
     * Initializes the queue, preparing it for use.
     */
    @Override
    public void initialize() {
        front = null;
        rear = null;
        size = 0;
    }
    
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

    /**
     * Construct empty linked queue
     */
    public DoublyLinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Adds an item to the back of the queue.
     * 
     * @param item the element to be added to the back of the queue
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
     * Removes and returns the item at the front of the queue.
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
     * Retrieves, but does not remove, the item at the front of the queue.
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
     * Retrieves, but does not remove, the item at the back of the queue.
     * 
     * @return the element at the back of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public E peekLast() {
        if (rear == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return rear.data;
    }

    /**
     * Adds an item to the front of the queue.
     * 
     * @param item the element to be added to the front of the queue
     */
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

    /**
     * Removes and returns the item at the back of the queue.
     * 
     * @return the element at the back of the queue
     * @throws IllegalStateException if the queue is empty
     */
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

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns the number of elements in the queue.
     * 
     * @return the size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the queue.
     */
    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Adds an item to the back of the queue, returning true upon success.
     * 
     * @param item the element to be added to the back of the queue
     * @return true if the element was added successfully
     */
    @Override
    public boolean offer(E item) {
        enqueue(item);
        return true;
    }

    /**
     * Retrieves and removes the head of the queue, or returns null if the queue is empty.
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
     * Retrieves, but does not remove, the head of this queue.
     * 
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public E element() {
        return peek();
    }


    /**
     * Converts the queue to an array.
     * 
     * @return an array of all elements in the queue
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
     * Provides an iterator to access each element in the queue without needing 
     * to directly interact with the queue's underlying structure.
     * 
     * @return an iterator over the elements in the queue
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

    /**
     * Checks if the queue contains a specific item.
     * 
     * @param item the element to check for
     * @return true if the queue contains the item, false otherwise
     */
    public boolean contains(E item) {
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
     * Removes the first occurrence of the specified element from the queue, if present.
     * 
     * @param item the element to be removed from the queue, if present
     * @return true if the queue contained the specified element and it was removed
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
}
