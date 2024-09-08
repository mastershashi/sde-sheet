import java.util.HashMap;
import java.util.Map;

class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final DoublyLinkedList<K, V> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.list = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        Node<K, V> node = cache.get(key);
        list.moveToFront(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.value = value;
            list.moveToFront(node);
        } else {
            if (cache.size() == capacity) {
                Node<K, V> tail = list.removeTail();
                cache.remove(tail.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            list.addToFront(newNode);
            cache.put(key, newNode);
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DoublyLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        DoublyLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addToFront(Node<K, V> node) {
            node.next = head.next;       // Step 1: Point the new node's next to the current first node
            node.prev = head;            // Step 2: Point the new node's prev to the head node
            head.next.prev = node;       // Step 3: Point the current first node's prev to the new node
            head.next = node;            // Step 4: Point the head node's next to the new node
        }

        void moveToFront(Node<K, V> node) {
            remove(node);
            addToFront(node);
        }

        void remove(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node<K, V> removeTail() {
            if (tail.prev == head) {
                return null;
            }
            Node<K, V> node = tail.prev;
            remove(node);
            return node;
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        System.out.println(lruCache.get(1)); // Outputs "One"
        lruCache.put(4, "Four");
        System.out.println(lruCache.get(2)); // Outputs null (2 has been evicted)
        lruCache.put(5, "Five");
        System.out.println(lruCache.get(3)); // Outputs null (3 has been evicted)
        System.out.println(lruCache.get(4)); // Outputs "Four"
        System.out.println(lruCache.get(5)); // Outputs "Five"
    }
}