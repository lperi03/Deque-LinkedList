/** My implementation of a double-ended queue (Deque), using a linked list.
 * Uses 2 sentinel nodes to increase efficiency
 * An alternate method would be to use a single revolving sentinel node
 */
import java.util.Iterator;


public class LinkedListDeque<Type> implements Iterable<Type> {

    private class Node {
        private Type item;
        private Node prev;
        private Node next;

        public Node(Type item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }
    private Node headSentinel;
    private Node tailSentinel;
    private int size;

    public LinkedListDeque() {
        headSentinel = new Node(null, null, null);
        tailSentinel = new Node(null, null, null);

        size = 0;
        headSentinel.next = tailSentinel;
        tailSentinel.prev = headSentinel;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void printDeque() {
        Node temp = headSentinel.next;
        while (temp != tailSentinel) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }

    public void addFirst(Type item) {
        Node temp = new Node(item, headSentinel, headSentinel.next);
        headSentinel.next.prev = temp;
        headSentinel.next = temp;
        size += 1;
    }

    public void addLast(Type item) {
        Node temp = new Node(item, tailSentinel.prev, tailSentinel);
        tailSentinel.prev.next = temp;
        tailSentinel.prev = temp;
        size += 1;
    }

    public Type removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Type temp = headSentinel.next.item;
        headSentinel.next = headSentinel.next.next;
        headSentinel.next.prev = headSentinel;
        size -= 1;
        return temp;
    }

    public Type removeLast() {
        if (isEmpty()) {
            return null;
        }
        Type temp = tailSentinel.prev.item;
        tailSentinel.prev = tailSentinel.prev.prev;
        tailSentinel.prev.next = tailSentinel;
        size -= 1;
        return temp;
    }

    public Type getIterative(int index) {
        int count = 0;
        Node temp = headSentinel.next;
        while (count < index) {
            temp = temp.next;
            count += 1;
        }
        return temp.item;
    }

    public Type getRecursive(int index) {
        return Helper(index, headSentinel.next);
    }

    public Type Helper(int index, Node node) {
        if (index == 0) {
            return node.item;
        }
        else {
            return Helper(index - 1, node.next);
        }
    }

    private class DequeIterator implements Iterator<Type> {
        private int position;
        public DequeIterator() {position = 0; }

        public boolean hasNext() {
            return position > size;
        }

        public Type next() {
            Node temp = headSentinel;
            Type result =getIterative(position);
            position++;
            return result;
        }
    }

    public Iterator<Type> iterator() {return new DequeIterator(); }



}
