public class LRUCache {

    private final Node head;
    private final Node tail;
    private final int capacity;
    private final Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.tail = head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
            if (map.size() > capacity) {
                removeLeastRecentlyUsed();
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void removeLeastRecentlyUsed() {
        Node toRemove = tail;
        removeNode(toRemove);
        map.remove(toRemove.key);
    }

    private void moveToHead(Node node) {
        if (node == head) {
            return;
        }
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private static class Node {
        final int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
