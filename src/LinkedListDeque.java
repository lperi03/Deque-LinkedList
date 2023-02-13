/** My implementation of a double-ended queue (Deque), using a linked list.
 * Uses 2 sentinel nodes to increase efficiency
 * An alternate method would be to use a single revolving sentinel node
 */
import java.util.Iterator;


public class LinkedListDeque<T> implements Iterable<T> {

    private class ItemNode {
        private T item;
        private ItemNode prev;
        private ItemNode next;

        public ItemNode(T item, ItemNode prev, ItemNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }
    private ItemNode headSentinel;
    private ItemNode tailSentinel;
    private int size;

    public LinkedListDeque() {
        headSentinel = new ItemNode(null, null, null);
        tailSentinel = new ItemNode(null, null, null);

        headSentinel.next = tailSentinel;
        tailSentinel.prev = headSentinel;
    }

}
