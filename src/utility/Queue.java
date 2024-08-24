package utility;

import java.util.Iterator;

/**
 * An interface for the Queue ADT.
 * 
 * @param <E> type of elements that the queue will store
 * 
 * This interface defines the essential operations for a queue data structure,
 * where elements are added to the back and removed from the front in a FIFO (First In, First Out) manner.
 * 
 * @author Heng Han Yee
 *         Ho Zhi Xuen
 */
public interface Queue<E> {
    /**
     * Initializes the queue, preparing it for use.
     */
    void initialize();

    /**
     * Adds an item to the back of the queue.
     */
    void enqueue(E item);

    /**
     * Removes and returns the item at the front of the queue.
     * 
     * @return at the front of the queue, or null if the queue is empty
     */
    E dequeue();

    /**
     * Retrieves, but does not remove, the item at the front of the queue.
     * 
     * @return the element at the front of the queue, or null if the queue is empty
     */
    E peek();

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     * 
     * @return the size of the queue
     */
    int size();

    /**
     * Removes all elements from the queue.
     */
    void clear();

    /**
     * Adds an item to the back of the queue, returning true upon success.
     * 
     * @return true if the element was added successfully, false otherwise
     */
    boolean offer(E item);

    /**
     * Retrieves and removes the head of the queue, or returns null if the queue is empty.
     * 
     * @return the element at the front of the queue, or null if the queue is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the head of this queue.
     * 
     * @return the element at the front of the queue
     */
    E element();

    /**
     * Convert the queue to an array.
     * 
     * @return an array of all elements in the queue
     */
    Object[] toArray();

    /**
     * Access each element in the queue without needing 
     * to directly interact with the queue's underlying structure.
     * 
     * @return an iterator over the elements in the queue
     */
    Iterator<E> iterator();

    /**
     * Retrieves, but does not remove, the item at the back of the queue.
     * 
     * @return the element at the back of the queue, or null if the queue is empty
     */
    E peekLast();

    /**
     * Adds an item to the front of the queue.
     * 
     * @param item the element to be added to the front of the queue
     */
    void addFirst(E item);

    /**
     * Removes and returns the item at the back of the queue.
     * 
     * @return the element at the back of the queue, or null if the queue is empty
     */
    E removeLast();

    /**
     * Retrieves, but does not remove, the item at the front of the queue.
     * 
     * @return the element at the front of the queue, or null if the queue is empty
     */
    E getFirst();

    /**
     * Retrieves, but does not remove, the item at the back of the queue.
     * 
     * @return the element at the back of the queue, or null if the queue is empty
     */
    E getLast();

    /**
     * Checks if the queue contains a specific element.
     * 
     * @param data the element to check for
     * @return true if the queue contains the element, false otherwise
     */
    boolean contains(E data);
}