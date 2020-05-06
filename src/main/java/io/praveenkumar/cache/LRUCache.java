package io.praveenkumar.cache;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of LRU Cache
 */
public class LRUCache implements Cache {
    /**
     * User defined name for the cache
     */
    private String cacheName;

    /**
     * The maximum number of elements that can be cached
     */
    private int cacheSize = 10; // default

    /**
     * LinkedList use to keep track of the items based on the usage
     */
    private LinkedList<Node> list = new LinkedList<>();

    /**
     * Cache items are stored in ConcurrentHashMap
     * This offers thread safe access of the cache elements.
     */
    private Map<String, Node> map = new ConcurrentHashMap<>();

    public LRUCache(String name, int cacheSize) {
        this.cacheName = name;
        this.cacheSize = cacheSize;
    }

    /**
     * Clear the data in cache
     */
    @Override
    public void clear() {
        list = new LinkedList<>();
        map = new ConcurrentHashMap<>();
    }

    /**
     * Remove an element from cache
     * @param key
     */
    @Override
    public void evict(String key) {
        if(map.containsKey(key)) {
            list.remove(map.get(key));
            map.remove(key);
        }
    }

    /**
     * Add a value to cache
     * @param key
     * @param value
     */
    @Override
    public void put(String key, String value) {
        Node node = new Node(key, value);
        if(map.containsKey(key)) {
            list.remove(map.get(key));
        }
        map.put(key, node);
        list.addFirst(node); // Recently added items are moved to the head
        while(list.size() > cacheSize) {
            map.remove(list.getLast().getKey());
            list.removeLast(); // Last item in the list is removed
        }
    }

    /**
     * Retrieve a value from cache
     * @param key
     * @return
     */
    @Override
    public String lookup(String key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            list.remove(node);
            list.addFirst(node); // Recently accessed items are moved to head
            return node.getValue();
        }
        return null;
    }

    @Override
    public String getName() {
        return cacheName;
    }

    /**
     * Helper method to simulate the working of cache
     */
    @Override
    public String getAllItems() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < list.size(); i++)
            sb.append(list.get(i).getKey()).append(":").append(list.get(i).getValue()).append(",");
        if (list.size() > 0) sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
