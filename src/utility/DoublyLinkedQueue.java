package utility;

import java.util.NoSuchElementException;
import java.util.Iterator;

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
    
        // Add element to the rear (enqueue)
        public void enqueue(E item) {
            Node newNode = new Node(item);
            if (rear == null) {
                // Queue is empty
                front = newNode;
                rear = newNode;
            } else {
                // Add to the end
                rear.next = newNode;
                newNode.prev = rear;
                rear = newNode;
            }
            size++;
        }
    
        // Remove element from the front (dequeue)
        public E dequeue() {
            if (front == null) {
                throw new IllegalStateException("Queue is empty");
            }
            E data = front.data;
            if (front == rear) {
                // Only one element
                front = null;
                rear = null;
            } else {
                front = front.next;
                front.prev = null;
            }
            size--;
            return data;
        }
    
        // Peek at the front element
        public E peek() {
            if (front == null) {
                throw new IllegalStateException("Queue is empty");
            }
            return front.data;
        }
    
        // Add element to the front
        public void addFirst(E item) {
            Node newNode = new Node(item);
            if (front == null) {
                // Queue is empty
                front = newNode;
                rear = newNode;
            } else {
                // Add to the front
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            }
            size++;
        }
    
        // Remove element from the rear
        public E removeLast() {
            if (rear == null) {
                throw new IllegalStateException("Queue is empty");
            }
            E data = rear.data;
            if (front == rear) {
                // Only one element
                front = null;
                rear = null;
            } else {
                rear = rear.prev;
                rear.next = null;
            }
            size--;
            return data;
        }

         // Get the first element in the queue
    public E getFirst() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    // Get the last element in the queue
    public E getLast() {
        if (rear == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return rear.data;
    }
    
        public boolean isEmpty() {
            return size == 0;
        }
    
        public int size() {
            return size;
        }

        @Override
        public void initialize() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'initialize'");
        }

        @Override
        public void clear() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'clear'");
        }

        @Override
        public boolean offer(E item) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'offer'");
        }

        public E poll() {
            if (front == null) {
                return null; // Queue is empty, return null
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

        @Override
        public E element() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'element'");
        }

        @Override
        public Object[] toArray() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'toArray'");
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
                
                // Optional: Implement remove if you want to support removal during iteration
                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
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
    
            // Traverse the queue to find the item
            while (current != null) {
                if (current.data.equals(item)) {
                    // If it's the only element in the queue
                    if (current == front && current == rear) {
                        front = null;
                        rear = null;
                    } else if (current == front) {
                        // If it's the front element
                        front = front.next;
                        if (front != null) {
                            front.prev = null;
                        }
                    } else if (current == rear) {
                        // If it's the rear element
                        rear = rear.prev;
                        if (rear != null) {
                            rear.next = null;
                        }
                    } else {
                        // If it's somewhere in the middle
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                    }
                    size--;
                    return true; // Element found and removed
                }
                current = current.next;
            }
            return false; // Element not found
        }

        
}
    

