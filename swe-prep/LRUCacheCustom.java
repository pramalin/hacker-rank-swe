import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class LRUCacheCustom {
    private final int capacity;
    private final HashMap<Integer, Node> cache;
    private final Node head; // dummy head (most recent)
    private final Node tail; // dummy tail (least recent)

    // Doubly linked list node
    private static class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCacheCustom(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);

        // Initialize dummy nodes
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // Move to front (most recently used)
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            // Key exists: update value and move to front
            node.value = value;
            moveToHead(node);
        } else {
            // Key doesn't exist: create new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            // Check capacity and evict if needed
            if (cache.size() > capacity) {
                Node lru = removeTail();
                cache.remove(lru.key);
            }
        }
    }

    // Add node right after dummy head
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Remove node from list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Move existing node to front
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    // Remove and return the LRU node (before dummy tail)
    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    public static void main(String[] args) throws IOException {
        //ArrayList<String> opsInput = new ArrayList<>(Arrays.asList("LRUCache","put","put","get","put","get","put","get","get","get"));
        //ArrayList<String>  valuesInput = new ArrayList<>(Arrays.asList("2","1,1","2,2","1","3,3","2","4,4","1","3","4"));
        ArrayList<String> opsInput = new ArrayList<>(Arrays.asList("LRUCache","put","put","get","put","put","get"));
        ArrayList<String>  valuesInput = new ArrayList<>(Arrays.asList("2","2,1","2,2","2","1,1","4,1","2"));

        LRUCacheCustom cache = null;
        for(int i = 0; i < opsInput.size(); i++) {
            String op = opsInput.get(i);
            String values = valuesInput.get(i);
            if ("LRUCache" == op) {
                cache = new LRUCacheCustom(Integer.parseInt(values));
                System.out.println("op: " + op + " value " + values);
            } else if("put" == op) {
                String[] kv = values.split(",");
                cache.put(Integer.parseInt(kv[0]), Integer.parseInt(kv[1]));
                System.out.println("op: " + op + " " + kv[0] + " -> " + kv[1]);
            } else if("get" == op) {
                Integer result = cache.get(Integer.parseInt(values));
                System.out.println("op: " + op + " key " + values + " result " + result);

            }
        }
    }
}