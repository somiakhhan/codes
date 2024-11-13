import java.util.*;
public class DoublyLinkedList<E> {
    private static class Node<E> {
        private E data;
        private Node<E> previous; // pointer to previous node
        private Node<E> next;     // pointer to next node

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public Node(E data) {
            this(data, null, null);
        }
    }

    private Node<E> head;   // pointer to the first node of this list
    private Node<E> tail;   // pointer to the last node of this list
    private int size;       // the number of nodes currently in this list

    public DoublyLinkedList() {
        tail = head = null;
        size = 0;
    }
    public void addFirst(E element) {
        head = new Node<>(element, null, head);
        size++;
        
        if(tail == null) {
            tail = head;
        } else {
            head.next.previous = head;
        }
    }
    public void addLast(E element) {
        if(isEmpty()) {
            addFirst(element);
        } else {
            tail = tail.next = new Node<>(element, tail, null);
            size++;
        }
    }
    public E getFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException("empty list");
        }
        return head.data;
    }
    public E getLast() {
        if(isEmpty()) {
            throw new NoSuchElementException("empty list");
        }
        return tail.data;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;

        while(current != null) {
            sb.append(current.data);

            if(current != tail) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    public String toReverseString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = tail;
        while(current != null) {
            sb.append(current.data);

            if(current != head) {
                sb.append(", ");
            }
            current = current.previous;
        }
        sb.append("]");
        return sb.toString();
    }
}

