package ADT;

/**
 * Node class to represent each element in the linked list.
 * 
 * @param <E> type of element stored in the node
 */
class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
        this.next = null;
    }
}
