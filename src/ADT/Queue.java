package ADT;

import java.util.Iterator;

/**
 * An interface for the Queue ADT with double-ended capabilities.
 * 
 * This interface defines the essential operations for a queue data structure,
 * where elements are added to the back and removed from the front in a FIFO (First In, First Out) manner.
 * Additionally, it provides double-ended operations similar to a deque.
 * 
 * @param <E> type of elements that the queue will store
 * 
 * Authors: Heng Han Yee
 *          Ho Zhi Xuen
 */
public interface Queue<E> {
    /**
     * Initializes the queue, preparing it for use.
     */
    void initialize();

    /**
     * Adds an item to the back of the queue.
     * 
     * @param item the element to be added to the back of the queue
     */
    void enqueue(E item);

    /**
     * Removes and returns the item at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    E dequeue();

    /**
     * Retrieves, but does not remove, the item at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    E peek();

    /**
     * Retrieves, but does not remove, the item at the back of the queue.
     * 
     * @return the element at the back of the queue
     * @throws IllegalStateException if the queue is empty
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
     * @return the element at the back of the queue
     * @throws IllegalStateException if the queue is empty
     */
    E removeLast();

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
     * @param item the element to be added to the back of the queue
     * @return true if the element was added successfully
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
     * @throws IllegalStateException if the queue is empty
     */
    E element();

    /**
     * Converts the queue to an array.
     * 
     * @return an array of all elements in the queue
     */
    Object[] toArray();

    /**
     * Provides an iterator to access each element in the queue without needing 
     * to directly interact with the queue's underlying structure.
     * 
     * @return an iterator over the elements in the queue
     */
    Iterator<E> iterator();

    /**
     * Checks if the queue contains a specific item.
     * 
     * @param item the element to check for
     * @return true if the queue contains the item, false otherwise
     */
    boolean contains(E item);

    /**
     * Removes the first occurrence of the specified element from the queue, if present.
     * 
     * @param item the element to be removed from the queue, if present
     * @return true if the queue contained the specified element and it was removed
     */
    boolean remove(E item);
}
