import java.io.IOException;
import java.util.*;

class LRUCache {
    int capacity = 0;
    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    LinkedList<Integer> order = new LinkedList<Integer>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public Integer get(int key) {
	    if(this.cache.containsKey(key)) {
            // Move the accessed key to the end (most recently used)
            this.order.removeFirstOccurrence(key);
            this.order.add(key);
            return this.cache.get(key);
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (this.cache.containsKey(key)) {
            // Update existing key
            this.order.removeFirstOccurrence(key);
        } else if (this.cache.keySet().size() >= this.capacity){
            // Remove the least recently used key
            Integer lruKey = this.order.removeFirst();
            this.cache.remove(lruKey);
        }
        // Add or update the key-value pair
        this.cache.put(key, value);
        this.order.add(key);
    }

    public static void main(String[] args) throws IOException {
        //ArrayList<String> opsInput = new ArrayList<>(Arrays.asList("LRUCache","put","put","get","put","get","put","get","get","get"));
        //ArrayList<String>  valuesInput = new ArrayList<>(Arrays.asList("2","1,1","2,2","1","3,3","2","4,4","1","3","4"));
        ArrayList<String> opsInput = new ArrayList<>(List.of("LRUCache","put","put","get","put","put","get"));
        ArrayList<String>  valuesInput = new ArrayList<>(List.of("2","2,1","2,2","2","1,1","4,1","2"));

        LRUCache cache = null;
        for(int i = 0; i < opsInput.size(); i++) {
            String op = opsInput.get(i);
            String values = valuesInput.get(i);
            if ("LRUCache" == op) {
                cache = new LRUCache(Integer.parseInt(values));
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

