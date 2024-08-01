import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An interface for the Queue ADT.
 * 
 * @param <T> The type of data that the queue stores.
 * 
 * @author Heng Han Yee
 */
public interface Queue<T> {
  
  /** Adds the given item to the back of the queue.
   * @param item the item to add
   */
  void enqueue(T item);
  
  /** Removes an item from the front of the queue and returns it.
   * @return the item at the front of the queue, or null if empty
   */
  T dequeue();
  
  /** Returns the item at the front of the queue, without removing it.
   * @return the item at the front of the queue, or null if empty
   */
  T peek();
  
  /** Returns true if the queue is empty.
   */
  boolean isEmpty();
  
  /** Removes all items from the queue. */
  void clear();
  
  /** Inserts the specified element into the queue. Returns true if successful.
   * @param item the item to add
   * @return true if the element was added successfully
   */
  boolean offer(T item);
  
  /** Retrieves and removes the head of the queue, or returns null if the queue is empty.
   * @return the item at the front of the queue, or null if empty
   */
  T poll();
  
  /** Returns the element at the front of the queue. Throws an exception if the queue is empty.
   * @return the item at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  T element();
  
  /** Converts the queue to an array.
   * @return an array containing all the items in the queue
   */
  Object[] toArray();
  
  /** Returns an iterator over the elements in the queue. */
  Iterator<T> iterator();
}
