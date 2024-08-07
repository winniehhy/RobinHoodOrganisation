
package ADT;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedQueue<E> implements Queue<E> {
    private Node<E> front;
    private Node<E> back;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedQueue() {
        initialize();
    }

    // Remove @Override annotation
    public void initialize() {
        front = null;
        back = null;
        size = 0;
    }

    // @Override
    public void enqueue(E item) {
        Node<E> newNode = new Node<>(item);
        if (isEmpty()) {
            front = newNode;
        } else {
            back.next = newNode;
        }
        back = newNode;
        size++;
    }

    // @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E item = front.data;
        front = front.next;
        if (front == null) {
            back = null;
        }
        size--;
        return item;
    }

    // @Override
    public E peek() {
        return isEmpty() ? null : front.data;
    }

    // @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // @Override
    public int size() {
        return size;
    }

    // @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }

    // @Override
    public boolean offer(E item) {
        enqueue(item);
        return true;
    }

    // @Override
    public E poll() {
        return dequeue();
    }

    // @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return front.data;
    }

    // @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node<E> current = front; current != null; current = current.next) {
            array[index++] = current.data;
        }
        return array;
    }

    // @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = front;

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

    // public static void main(String[] args) {
    //     LinkedQueue<Integer> queue = new LinkedQueue<>();
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);

    //     System.out.println("Queue size: " + queue.size());
    //     System.out.println("Front element: " + queue.peek());
    //     System.out.println("Dequeue: " + queue.dequeue());
    //     System.out.println("Queue size after dequeue: " + queue.size());
    //     System.out.println("Is queue empty? " + queue.isEmpty());
    // }
}