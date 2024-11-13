import java.util.*;
public class DoublyLinkedList<E> implements Iterable<E> {
    private static class Node<E> {
        private E data;
        private Node<E> previous;
        private Node<E> next;

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public Node(E data) {
            this(data, null, null);
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {
        tail = head = null;
        size = 0;
    }

    public void addFirst(E element) {
        head = new Node<>(element, null, head);
        size++;

        if (tail == null) {
            tail = head;
        } else {
            head.next.previous = head;
        }
    }

    public void addLast(E element) {
        if (isEmpty()) {
            addFirst(element);
        } else {
            tail = tail.next = new Node<>(element, tail, null);
            size++;
        }
    }

    public boolean add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("No element");
        }
        addLast(e);
        return true;
    }
	public E get(int index) {
   Objects.checkIndex(index, size);
   
   Node<E> current = head;
   for(int i = 0; i < index; i++) {
    current = current.next;
   }
   return current.data;
}
public E set(int index, E element) {
    Objects.checkIndex(index, size);
    
    Node<E> current = head;
    for(int i = 0; i < index; i++) {
       current = current.next;
    }
    E old = current.data;
    current.data = element;
    return old;
    
}
public void add(int index, E element) {
    if (index < 0 || index > size) {  
    	throw new IndexOutOfBoundsException("Invalid");
}
    if(index == 0) {
        addFirst(element);
    } else if(index == size) {
        addLast(element);
    } else {
        Node<E> current = head;
        for(int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node<E> newNode = new Node<>(element);
        newNode.next = current.next;
        newNode.previous = current;
        current.next.previous = newNode;
        current.next = newNode;
        size++;
    
    }
    }
    public E remove(int index) {
        if (index < 0 || index >= size) {  
    	throw new IndexOutOfBoundsException("Invalid");
        }

        if(index == 0) {
            return removeFirst();
        } else if(index == size - 1) {
            return removeLast();
        } else {
            Node<E> current = head;

            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            E result = current.data;
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
            return result;
    }
}

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty list");
        }
        return head.data;
    }

    public E getLast() {
        if (isEmpty()) {
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

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty list");
        }
        E result = head.data;
        head = head.next;
        size--;

        if (head != null) {
            head.previous = null;
        } else if (head == null) {
            tail = null;
        }
        return result;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty list");
        }
        E result = tail.data;
        tail = tail.previous;
        size--;

        if (tail == null) {
            head = null;
        } else if (tail != null) {
            tail.next = null;
        }
        return result;
    }

    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.previous = null;
                    } else {
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = current.previous;
                    tail.next = null;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false; 
    }

    public void clear() {
        Node<E> current = head;
        while (current != null) {
            Node<E> nextNode = current.next;
            current.previous = null;
            current.next = null;
            current = nextNode;
        }
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Object o) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int indexOf(Object o) {
    	Node<E> current = head; 
    	for (int i = 0; i < size; i++) {
    		if (Objects.equals(current.data, o)) {
    			return i; 
    			}
    			current = current.next; 
    			}
    			return -1; 
    			}

    public int lastIndexOf(Object o) {
    Node<E> current = tail; 
    for (int i = size - 1; i >= 0; i--) { 
    	if (Objects.equals(current.data, o)) {
    		return i; 
    		}
    		current = current.previous; 
    		}
    		return -1; 
    		}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;

        while (current != null) {
            sb.append(current.data);

            if (current != tail) {
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
        while (current != null) {
            sb.append(current.data);

            if (current != head) {
                sb.append(", ");
            }
            current = current.previous;
        }
        sb.append("]");
        return sb.toString();
    }
@Override
public Iterator<E> iterator() {
    return new DoublyLinkedListIterator();
}
private class DoublyLinkedListIterator implements Iterator<E> {
    private Node<E> current;
    
public DoublyLinkedListIterator() {
    current = head;
}
@Override
public boolean hasNext() {
    return current != null;
}
@Override
public E next() {
    if(!hasNext()) {
        throw new NoSuchElementException();
    }
    E result = current.data;
    current = current.next;
    return result;
}
}
}
