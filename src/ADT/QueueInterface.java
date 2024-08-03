import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An interface for the Queue ADT.
 * 
 * @param <E> type of elements that the queue will store
 * 
 * This interface defines the essential operations for a queue data structure,
 * where elements are added to the back and removed from the front in a FIFO (First In, First Out) manner.
 * 
 * @author Heng Han Yee
 */
public interface Queue<E> {
  
  /** Adds the given item to the back of the queue.
   * @param item to be added to the queue
   */
  void enqueue(E item);
  
  /** Removes an item from the front of the queue and returns it.
   * @return the item at the front of the queue, or null if empty
   */
  E dequeue();
  
  /** Returns the item at the front of the queue, without removing it.
   * @return the item at the front of the queue, or null if empty
   */
  E peek();
  
  /** Returns true if the queue is empty.
   */
  boolean isEmpty();
  
  /** Removes all items from the queue. */
  void clear();
  
  /** Inserts the specified element into the queue. Returns true if successful.
   * @param item to be added to the queue
   * @return true if the element was added successfully
   */
  boolean offer(E item);
  
  /** Retrieves and removes the head of the queue, or returns null if the queue is empty.
   * @return the item at the front of the queue, or null if empty
   */
  E poll();
  
  /** Returns the element at the front of the queue. Throws an exception if the queue is empty.
   * @return the item at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  E element();
  
  /** Converts the queue to an array.
   * @return an array containing all the items in the queue
   */
  Object[] toArray();
  
  /** Returns an iterator over the elements in the queue. */
  Iterator<E> iterator();
}
