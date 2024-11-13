import java.util.Objects;

public class ArrayStringBoundedList implements StringBoundedList {
    private final String[] elements;
    private int size;
	
	@SuppressWarnings("unchecked")
    public ArrayStringBoundedList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        elements = new String[capacity];
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
        size = 0;
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
