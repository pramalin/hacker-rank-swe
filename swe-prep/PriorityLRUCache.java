import java.io.IOException;
import java.util.*;

class PriorityLRUCache {
    class Node {
        int key, value, priority;
        Node prev, next;
        Node(int k, int v, int p) { this.key = k; this.value = v; this.priority = p; }
    }

    class DLList {
        Node head, tail;
        DLList() {
            head = new Node(0, 0, 0);
            tail = new Node(0, 0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node n) {
            n.next = head.next;
            n.prev = head;
            head.next.prev = n;
            head.next = n;
        }

        void remove(Node n) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }

        Node removeLast() {
            if (isEmpty()) return null;
            Node res = tail.prev;
            remove(res);
            return res;
        }

        boolean isEmpty() { return head.next == tail; }
    }

    private int capacity, size;
    private Map<Integer, Node> cache;
    private TreeMap<Integer, DLList> priorityGroups;

    public PriorityLRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();
        this.priorityGroups = new TreeMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node n = cache.get(key);
        // Move to front of its priority list (LRU update)
        priorityGroups.get(n.priority).remove(n);
        priorityGroups.get(n.priority).addFirst(n);
        return n.value;
    }

    public void put(int key, int value, int priority) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            updatePriority(key, priority); // Re-use logic to handle movement
            n.value = value;
            return;
        }

        if (size >= capacity) {
            evict();
        }

        Node n = new Node(key, value, priority);
        cache.put(key, n);
        priorityGroups.computeIfAbsent(priority, k -> new DLList()).addFirst(n);
        size++;
    }

    public void updatePriority(int key, int newPriority) {
        if (!cache.containsKey(key)) return;
        Node n = cache.get(key);

        // Remove from old priority list
        DLList oldList = priorityGroups.get(n.priority);
        oldList.remove(n);
        if (oldList.isEmpty()) priorityGroups.remove(n.priority);

        // Update and move to new priority list
        n.priority = newPriority;
        priorityGroups.computeIfAbsent(newPriority, k -> new DLList()).addFirst(n);
    }

    private void evict() {
        // TreeMap keeps keys sorted, so firstKey() is the lowest priority
        int lowestP = priorityGroups.firstKey();
        DLList list = priorityGroups.get(lowestP);
        Node toEvict = list.removeLast();

        if (toEvict != null) {
            cache.remove(toEvict.key);
            if (list.isEmpty()) priorityGroups.remove(lowestP);
            size--;
        }
    }
}

class Result {

    /*
     * Complete the 'simulatePriorityCache' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER capacity
     *  2. INTEGER numOperations
     *  3. STRING_ARRAY operationTypes
     *  4. INTEGER_ARRAY keys
     *  5. INTEGER_ARRAY values
     *  6. INTEGER_ARRAY priorities
     */

    public static List<Integer> simulatePriorityCache(int capacity, int numOperations, List<String> operationTypes, List<Integer> keys, List<Integer> values, List<Integer> priorities) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        PriorityLRUCache c = new PriorityLRUCache(capacity);
        Iterator<String> opIt = operationTypes.iterator();
        Iterator<Integer> keyIt = keys.iterator();
        Iterator<Integer> valueIt = values.iterator();
        Iterator<Integer> priIt = priorities.iterator();

        for(int i = 0; i < numOperations; i++) {
            String op = opIt.next();
            Integer key = keyIt.next();
            Integer value = valueIt.next();
            Integer priority = priIt.next();

            if("put".equals(op)) {
                c.put(key, value, priority);
            } else if ("get".equals(op)) {
                result.add(c.get(key));
            } else if ("updatePriority".equals(op)) {
                c.updatePriority(key, priority);
            }
        }

        return result;
    }
    

public static void main(String[] args) throws IOException {

        int capacity = 2;
        int numOperations = 9;
        List<String> operationTypes = List.of("put", "put", "get", "put", "get", "updatePriority", "put", "get", "get");
        List<Integer> keys = List.of(1, 2, 1, 3, 2, 3, 4, 3, 4);
        List<Integer> values = List.of(10, 20, 0, 30, 0, 0, 40, 0, 0);
        List<Integer> priorities = List.of(1, 2, 0, 1, 0, 3, 1, 0, 0);
        
        List<Integer> result = simulatePriorityCache(capacity, numOperations, operationTypes, keys, values, priorities);
        System.out.println("result: " + result);
    }
}
