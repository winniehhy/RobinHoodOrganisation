package utility;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Author: Heng Han Yee
 *         Ho Zhi Xuen
 */

public class DoublyLinkedQueue<E> implements Queue<E>, Iterable<E> {
    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public DoublyLinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

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

    public E peek() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    public E peekLast() {
        if (rear == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return rear.data;
    }
    
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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public void initialize() {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public boolean offer(E item) {
        enqueue(item);
        return true;
    }

    @Override
    public E poll() {
        if (front == null) {
            return null;
        }
        return dequeue();
    }

    @Override
    public E element() {
        return peek();
    }

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

    public E getFirst() {
        return peek();
    }

    public E getLast() {
        return peekLast();
    }

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