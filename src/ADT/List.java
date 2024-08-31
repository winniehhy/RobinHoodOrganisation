package ADT;

import java.util.Iterator;

/**
 * A generic List interface that defines the basic operations
 * for managing a collection of elements.
 * 
 * @param <E> the type of elements in this list
 * 
 * Authors: Ho Zhi Xuen
 */
public interface List<E> extends Iterable<E> {

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns an iterator over the elements in this list.
     * This iterator provides a means to traverse the list.
     * 
     * @return an iterator over the elements in this list
     */
    Iterator<E> iterator();

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param e the element to be appended to this list
     * @return true if the list changed as a result of the operation
     */
    boolean add(E e);

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    void clear();

    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range 
     *         (index < 0 || index >= size())
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list 
     * with the specified element.
     * 
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     */
    E set(int index, E element);
}
