import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.HashMap;

class LRUCacheCustom {
    class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DLList {
        Node head, tail;
        DLList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // Always add to the front (MRU)
        void addFirst(Node n) {
            n.next = head.next;
            n.prev = head;
            head.next.prev = n;
            head.next = n;
        }

        void addLast(Node n) {
            // 1. Point the new node to its neighbors
            n.next = tail;
            n.prev = tail.prev;

            // 2. Update the old last node's 'next' to point to our new node
            tail.prev.next = n;

            // 3. Update the tail's 'prev' to point to our new node
            tail.prev = n;
        }
        
        void remove(Node n) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }

        Node removeLast() {
            if (head.next == tail) return null;
            Node res = tail.prev;
            remove(res);
            return res;
        }
    }

    int capacity;
    HashMap<Integer, Node> cache;
    DLList order = new DLList();

    public LRUCacheCustom(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;

        // Move to front: remove then re-add
        order.remove(node);
        order.addFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            node.value = value;
            order.remove(node); // Critical: remove before re-adding
            order.addFirst(node);
        } else {
            if (cache.size() >= capacity) {
                Node lru = order.removeLast();
                if (lru != null) cache.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            order.addFirst(newNode);
        }
    }



    public static void main(String[] args) throws IOException {
        ArrayList<String> opsInput = new ArrayList<>(Arrays.asList("LRUCache","put","put","get","put","get","put","get","get","get"));
        ArrayList<String>  valuesInput = new ArrayList<>(Arrays.asList("2","1,1","2,2","1","3,3","2","4,4","1","3","4"));
        //ArrayList<String> opsInput = new ArrayList<>(Arrays.asList("LRUCache","put","put","get","put","put","get"));
        //ArrayList<String>  valuesInput = new ArrayList<>(Arrays.asList("2","2,1","2,2","2","1,1","4,1","2"));

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