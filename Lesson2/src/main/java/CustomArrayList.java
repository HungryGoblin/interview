public class CustomArrayList<T> {

    private static final int DEFAULT_CAPACITY = 2;

    private int size;

    private int capacity;

    private T[] elements;

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    private void ensureCapacity() {
        if (capacity <= size + 1) {
            T[] temp = (T[]) new Object[capacity + DEFAULT_CAPACITY];
            System.arraycopy(elements, 0, temp, 0, capacity);
            capacity = temp.length;
            elements = temp;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(String.format(
                    "Incorrect index: %d, expected: 0..%d", index, size - 1));
    }

    public void add(T element) {
        ensureCapacity();
        elements[size] = element;
        size++;
    }

    public void remove(int index) {
        checkIndex(index);
        if (index < size - 1)
            System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < size; i++) {
            ret = String.format("%s [%s]", ret, elements[i].toString());
        }
        return ret.trim();
    }

    public CustomArrayList() {
        this.capacity = capacity;
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

}
