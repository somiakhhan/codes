import java.util.Objects;

public class ArrayStringBoundedList implements StringBoundedList {
    private final String[] elements;
    private int size;

    public ArrayStringBoundedList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        elements = new String[capacity];
        size = 0;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(String s) {
        if (isFull()) {
            throw new IllegalStateException("List is full");
        }
        elements[size] = s;
        size++;
    }

    @Override
    public void add(int index, String s) {
        if (isFull()) {
            throw new IllegalStateException("List is full");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = s;
        size++;
    }

    @Override
public String remove(int index) {
    Objects.checkIndex(index, size);
        String result = elements[index];

        for(int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
           }
           size--;
            return result;
        }


    @Override
    public boolean remove(String s) {
        int index = indexOf(s);
        if (index < 0) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return elements[index];
    }

    @Override
    public String set(int index, String element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        String previousElement = elements[index];
        elements[index] = element;
        return previousElement;
    }

    @Override
    public int indexOf(String s) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], s)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String s) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], s)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public String getFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return get(0);
    }

    @Override
    public String setFirst(String element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return set(0, element);
    }

    @Override
    public String getLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return get(size - 1);
    }

    @Override
    public String setLast(String element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return set(size - 1, element);
    }

    @Override
    public boolean contains(String s) {
        return indexOf(s) >= 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
