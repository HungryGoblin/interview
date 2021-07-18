public class CustomLinkedList<T> {

    private int size = 0;

    private Node head;

    class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            next = null;
        }
    }

    public int getSize() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(String.format(
                    "Incorrect index: %d, expected: 0..%d", index, size - 1));
    }

    private Node getNode(int index) {
        checkIndex(index);
        if (index == 0) return head;
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public void insert(T data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        } else {
            Node last = getNode(size - 1);
            last.next = node;
        }
        size++;
    }

    public void remove(int index) {
        if (index == 0) {
            if (size <= 1)
                head = null;
            else
                head = head.next;
        } else {
            Node node = getNode(index - 1);
            node.next = index >= size - 1 ? null : getNode(index + 1);
        }
        size--;
    }


    @Override
    public String toString() {
        Node node = head;
        String ret = "";
        if (head == null)
            return null;
        for (int i = 0; i < size; i++) {
            ret = String.format("%s [%s]", ret, get(i));
            node = node.next;
        }
        return ret.trim();
    }

}
